import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author Zakary Schafstall
 * @author Porndanai Amporndanai
 * @author Geoffrey Mickey
 */
public class CGrep {
	
	/**
	 * This list will contain all the Future objects that will provide us with
	 * the search results for the specific file they were assigned to.
	 */
	static ArrayList<Future<Found>> futureList = new ArrayList<Future<Found>>();
	
	/**
	 * Manages the starting of threads for the search tasks
	 */
	static ExecutorService executor;
	
	/**
	 * The number of threads to keep in the executor's thread pool
	 */
	final static int numThreads = 3;
	
	
	/**
	 * Searches through a given list of files for any strings specified
	 * by the given regular expression.
	 * 
	 * @param args - Command line arguments, where the first argument is the 
	 * regular expression to use and any subsequent arguments are the files 
	 * to be searched.
	 */
	public static void main(String[] args) {
		executor = Executors.newFixedThreadPool(numThreads);
		
		//Submit a search task for each file
		for(int i = 1; i < args.length; i++){
			
			futureList.add(executor.submit(new Grep(args[i], args[0])));
			
		}
		
		//Now that all tasks have been submitted, we wait for them to finish,
		//printing each one as they finish.
		for(Future i : futureList){
			
			try {
				System.out.println(i.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
		}
		
		//Ensures the termination of the executor service.
		executor.shutdown();
		
	}

}
