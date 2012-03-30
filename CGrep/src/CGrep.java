import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class CGrep {

	/**
	 * @param args
	 */
	
	static ArrayList<Future<Found>> futureList = new ArrayList<Future<Found>>();
	static ExecutorService executor;
	final static int numThreads = 3;
	
	public static void main(String[] args) {
		executor = Executors.newFixedThreadPool(numThreads);
		
		
		for(int i = 1; i < args.length-1; i++){
			
			futureList.add(executor.submit(new Grep(args[i], args[0])));
			
		}
		
		for(int i = 0; i < futureList.size() ;i++){
			System.out.println(futureList.get(i).toString());
		}
		
		
	}

}
