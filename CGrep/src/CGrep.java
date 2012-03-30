import java.util.ArrayList;
import java.util.concurrent.Future;


public class CGrep {

	/**
	 * @param args
	 */
	
	ArrayList<Future<Found>> futureList = new ArrayList<Future<Found>>();
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		
		for(int i = 1; i < args.length-1; i++){
			
			futureList.add(executor.submit());
			
		}
		
		
	}

}
