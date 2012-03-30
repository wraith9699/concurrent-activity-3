import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author Zakary Schafstall
 * @author Porndanai Amporndanai
 * @author Geoffrey Mickey
 * 
 * This class is a thread that handles the task of searching through
 * a given file, collecting lines that contain substrings matching
 * a given regular expression.
 */
public class Grep implements Callable<Found>{

	/**
	 * The file to be searched
	 */
	private File currentFile;
	
	/**
	 * The compiled regular expression to use when searching
	 */
	private Pattern pattern;
	
	/**
	 * The matcher generated from the pattern, associated with an input string
	 * to check for matches
	 */
	private Matcher patternMatcher;
	
	/**
	 * Found object that holds the results of the search
	 */
	private Found result;


	/**
	 * Constructor that initializes a file object and a "Found" object
	 * 
	 * @param fileName String that specifies the name/path of the file to be searched
	 * @param pattern String representation of the regular expression to use in
	 * the search
	 */
	public Grep(String fileName, String pattern) {
		currentFile = new File(fileName);
		result = new Found(fileName);
		
		compile(pattern);
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Found call() throws IOException {
		
		BufferedReader fileInput = new BufferedReader(new FileReader(currentFile));
		int lines = 0;  //Keep track of which line in the file we are reading
		String currentLine;
		
		//Continue reading lines until the end of the file, adding results to
		// the Found object as they are found.
		while ((currentLine = fileInput.readLine()) != null) {
			
			if (patternMatcher == null) {
				//Create a new matcher associated with the current line
				patternMatcher = pattern.matcher(currentLine);
			} else {
				//Associate the matcher with the new line
				patternMatcher.reset(currentLine);
			}
			
			if (patternMatcher.find()) {
				//Add the line information to the result object
				result.getResultList().add(lines + " " + currentLine);
			}
			
			lines++;
		}
		
		fileInput.close();
		
		return result;
	}

	/**
	 * Given a string representation of a regular expression,
	 * compiles the string into a pattern object to be used for matching.
	 * 
	 * @param regex String representing the regular expression to compile
	 */
	private void compile(String regex){
		try {
			pattern = Pattern.compile(regex);
		} catch (PatternSyntaxException x){
			System.err.println(x.getMessage());
			System.exit(1);
		}
	}


}
