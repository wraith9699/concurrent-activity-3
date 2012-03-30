import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;



public class Grep implements Callable<Found>{

	private File currentFile;
	private Pattern pattern;
	private Matcher patternMatcher;
	private Found result;


	public Grep(String fileName, String pattern) {
		currentFile = new File(fileName);
		result = new Found(fileName);
		
		compile(pattern);
	}

	@Override
	public Found call() throws IOException {
		
		BufferedReader fileInput = new BufferedReader(new FileReader(currentFile));
		int lines = 0;
		String currentLine;
		
		while ((currentLine = fileInput.readLine()) != null){
			if (patternMatcher == null) {
				patternMatcher = pattern.matcher(currentLine);
			} else {
				patternMatcher.reset(currentLine);
			}
			if (patternMatcher.find()) {
				result.getResultList().add(lines + " " + currentLine);
			}
			lines++;
		}
		
		fileInput.close();
		
		return result;
	}

	private void compile(String regex){
		try {
			pattern = Pattern.compile(regex);
		} catch (PatternSyntaxException x){
			System.err.println(x.getMessage());
			System.exit(1);
		}
	}


}
