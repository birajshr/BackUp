import java.util.Random;
import java.util.concurrent.*;

public class PSort implements Runnable {
	public static ExecutorService threadPool;
	
		private int begin;
		private int end;
		private int[] A;

		public PSort(int[] a, int begin, int end) {
			this.begin = begin;
			this.end = end;
			this.A = a;
		}

		public void run() {
			if (end == -1) return;
			
			int pivot = begin + (end - begin) / 2;
			int pivot_value = A[pivot];

			int firstPart = begin;
			int secondPart = end;

			while (firstPart <= secondPart) {
				while (A[firstPart] < pivot_value)  { firstPart++;  }
				while (A[secondPart] > pivot_value) { secondPart--; }
				if (firstPart <= secondPart) {
					swap(A, firstPart, secondPart);
					firstPart++;
					secondPart--;
				}
			}
			Future<?> firstHalf = null;
			if (begin < secondPart) {
				// if we have an unsorted first half, recurse on it
				firstHalf = threadPool.submit(new PSort(A, begin, secondPart));
			}
			Future<?> secondHalf = null;
			if (firstPart < end) {
				// if we have an unsorted second half, recurse on it
				secondHalf = threadPool.submit(new PSort(A, firstPart, end));
			}
			if (firstHalf != null) {
				try {
					firstHalf.get();
				} catch (Exception exc) {
					// TODO
					System.out.println("EXCEPTION");
				}
			}
			if (secondHalf != null) {
				try {
					secondHalf.get();
				} catch (Exception exc) {
					// TODO
					System.out.println("EXCEPTION");
				}
			}
		}


		public static void swap(int[] a, int beginning, int end) {
			int temp = a[beginning];
			a[beginning] = a[end];
			a[end] = temp;
		}
	

	public static void parallelSort(int[] a, int begin, int end) {
		threadPool = Executors.newCachedThreadPool();
		Future<?> future = threadPool.submit(new PSort(a, begin, end-1));
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadPool.shutdown();

	}

}//DONE