/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Sun Oct  6 16:28:17 CEST 2024
 */

package uebung04.as.aufgabe02;

import java.util.Arrays;
import java.util.Random;

public class PriorityQueueTest {

  private static void stressTest() throws FullPriorityQueueException {
    System.out.print("\nStress-Test: ... ");
    final int NUMBER_OF_TESTS = 1_000_000;
    final int LENGTH_RANGE = 10;
    final int DATA_RANGE = 10;
    Random random = new Random(1);
    for (int testNr = 0; testNr < NUMBER_OF_TESTS; testNr++) {
      int length = (int) (random.nextDouble() * LENGTH_RANGE + 1);
      int[] array = new int[length];
      for (int i = 0; i < length; i++) {
        int number = (int) (random.nextDouble() * DATA_RANGE + 1);
        array[i] = number;
      }
      PriorityQueue<Integer, String> ourPQ = new PriorityQueue<>(length);
      java.util.PriorityQueue<Integer> javaPQ = new java.util.PriorityQueue<>();
      for (int i : array) {
        ourPQ.insert(i, "Value_"+i);
        javaPQ.add(i);
      }
      for (int i = 0; i < array.length; i++) {
        if (ourPQ.size() != javaPQ.size()) {
          System.out.println("ERROR: wrong size!");
          System.out.println("Array: " + Arrays.toString(array));
          System.exit(1);
        }
        if (!ourPQ.removeMin().getKey().equals(javaPQ.poll())) {
          System.out.println("ERROR: wrong removeMin()!");
          System.out.println("Array: " + Arrays.toString(array));
          System.exit(1);
        }
      }
      if (ourPQ.removeMin() != null) {
        System.out.println("ERROR: removeMin() != null");
        System.out.println("Array: " + Arrays.toString(array));
        System.exit(1);
      }
    }
    System.out.println("o.k.");
  }
  
  public static void main(String[] args) throws FullPriorityQueueException {

    PriorityQueue<Integer, String> pq = 
        new PriorityQueue<>(7);
        //new PriorityQueueADV<>(7, "Uebung 4:PQ", 2, 2);

    System.out.println("insert()'s: ");
    pq.print();
    pq.insert(4, "D");
    pq.print();
    pq.insert(5, "E");
    pq.print();
    pq.insert(3, "C");
    pq.print();
    pq.insert(2, "B");
    pq.print();
    pq.insert(1, "A");
    pq.print();
    System.out.println("\nmin(): " + pq.min());
    while (pq.size() > 1) {
      System.out.println("removeMin(): " + pq.removeMin());
      pq.print();
    }
    
    stressTest();

  }

}

/* Session-Log:

insert()'s: 
[null, null, null, null, null, null, null, null]
[null, [(4,D),1], null, null, null, null, null, null]
[null, [(4,D),1], [(5,E),2], null, null, null, null, null]
[null, [(3,C),1], [(5,E),2], [(4,D),3], null, null, null, null]
[null, [(2,B),1], [(3,C),2], [(4,D),3], [(5,E),4], null, null, null]
[null, [(1,A),1], [(2,B),2], [(4,D),3], [(5,E),4], [(3,C),5], null, null]

min(): (1,A)
removeMin(): (1,A)
[null, [(2,B),1], [(3,C),2], [(4,D),3], [(5,E),4], null, null, null]
removeMin(): (2,B)
[null, [(3,C),1], [(5,E),2], [(4,D),3], null, null, null, null]
removeMin(): (3,C)
[null, [(4,D),1], [(5,E),2], null, null, null, null, null]
removeMin(): (4,D)
[null, [(5,E),1], null, null, null, null, null, null]

Stress-Test: ... o.k.

*/
