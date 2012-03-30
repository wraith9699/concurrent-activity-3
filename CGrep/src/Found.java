import java.util.ArrayList;
import java.util.List;

/**
 * @author Zakary Schafstall
 * @author Porndanai Amporndanai
 * @author Geoffrey Mickey
 * 
 * This class holds results found by a Grep object, implementing and 
 * overriding the printable output of the results.
 */
public class Found {

	/**
	 * Associated file that was searched by Grep, and from which the
	 * search results come from.
	 */
	private String fileName;
	
	//List of strings that contain substrings matching a regular expression
	//that was found by a Grep object.
	private List <String> resultList;
	
	/**
	 * Typical Constructor
	 * 
	 * @param fileName Name of the file from which the contained results came from
	 */
	public Found (String fileName) {
		
		this.fileName = fileName;
		this.resultList = new ArrayList <String> ();
		
	}

	/**
	 * Retrieve the result list
	 * 
	 * @return resultList that contains the search results that have been found so far 
	 */
	public List<String> getResultList() {
		return resultList;
	}
	
	@Override
	public String toString(){
		String toPrint = "";
		for (String i : resultList) {
			toPrint = toPrint.concat(i + "\n");
		}
		
		return toPrint;
	}
	
}
