
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;

import java.util.Random;

 

import org.junit.Before;

import org.junit.Test;

public class MinHeapArrayInvariant1Test {

   private static final long SEED = 42;
   private static final long VALUES_TO_TEST = 1000;
   private Random random;
   private HeapArray<Integer> heap;

   @Before
   public void setUp() {
       random = new Random(SEED);
       heap = new HeapArray<Integer>();
   }

 

   @Test
   public void testWithRandomAdds() {
       for (int i=0; i < VALUES_TO_TEST; i++) {
           int addMe = random.nextInt();
           heap.add(addMe);
           assertTrue(elementsHold(i));
       }

   }

 

   @Test
   public void testWithRandomRemoves() {
       fillWithRandomValues(VALUES_TO_TEST);
       while (heap.size() > 0) {
           int indexToRemove = random.nextInt(heap.size());
           Integer removeMe = heap.get(indexToRemove);
           heap.remove(removeMe);
           assertTrue(elementsHold(indexToRemove));
       }
   }

 

   @Test
   public void testPop() {
       fillWithRandomValues(VALUES_TO_TEST);
       while (heap.size() > 0) {
           heap.pop();
           assertTrue(elementsHold(heap.size()));
       }
   }

 

   @Test
   public void testClear() {
       fillWithRandomValues(VALUES_TO_TEST);
       heap.clear();
       //assertTrue(invariantHolds());
       assertTrue(elementsHold(0));
   }

   private boolean invariantHolds() {
       Integer top = heap.peek();
       if (top == null) {
           return true;
       }

       Integer[] contents = new Integer[heap.size()];
       Integer min = Collections.min(Arrays.asList(heap.toArray(contents)));

       if (min > top) {
           System.out.println("Whoops!");
       }
       return min <= top;
   }
      
   private boolean elementsHold(int size)
   {
	   int heapSize = heap.size();
	   int TwoSPlus1 = (2*size) + 1;
	   int TwoSPlus2 = (2*size) + 2;
	   
	   if (heapSize < size || heapSize < TwoSPlus1 || heapSize < TwoSPlus2)
		   return true;
	   if (heap.get(size) <= heap.get(TwoSPlus1) || heap.get(size	) <= heap.get(TwoSPlus2))
		   return true;
	   return false;   
   }
   

   private void fillWithRandomValues(long numValues) {
       for (int i = 0; i < numValues; i++){
           heap.add(random.nextInt());
       }
   }
   
   

}