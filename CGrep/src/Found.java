import java.util.ArrayList;
import java.util.List;

public class Found {

	private String fileName;
	private List <String> resultList;
	
	public Found (String fileName) {
		this.fileName = fileName;
		this.resultList = new ArrayList <String> ();
	}

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
