/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Sun Sep 15 12:21:02 CEST 2024
 */

package uebung01.as.aufgabe03;


public class RecursiveSum {

  
  public static int recursiveSum(int n) {
	  if (n == 0) {
	        return 0;
	    } else {
	        return n + recursiveSum(n - 1);
	    }
	}
  


  public static void main(String[] args) {
    int n = 100;
    System.out.println("Sum of 0..100 recursively                    = " + 
        recursiveSum(n));
    System.out.println("Sum of 0..100 explicitly : n * (n + 1) / 2)  = " + 
        (n * (n + 1) / 2));
  }
}


/* Session-Log:

Sum of 0..100 recursively                    = 5050
Sum of 0..100 explicitly : n * (n + 1) / 2)  = 5050

*/
