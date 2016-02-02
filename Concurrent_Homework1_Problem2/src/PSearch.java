import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PSearch implements Callable<Integer>{
  
	 public ExecutorService threadPool = Executors.newCachedThreadPool();
	 private int search;
	 private int[] array;
	 
	 public PSearch(int k, int[] A)
	 {
		 this.search = k;
		 this.array = A;
	 }
	 
	public static int parallelSearch(int k, int[] A, int numThreads){
    // TODO: Implement your parallel search function 
	  
	    try {
	    	ExecutorService es = Executors.newFixedThreadPool(numThreads);
	            PSearch P = new PSearch(k,A);
	            Future<Integer> f1 = es.submit(P);
	            System.out.println("Answer is " + f1.get());
	            es.shutdown ();
	        } catch (Exception e) { System.err.println (e); }
	    
    return -1;
  }

	
@Override
public Integer call() throws Exception {

	return null;
}
}
