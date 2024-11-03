/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Sun Oct  6 16:28:17 CEST 2024
 */

package uebung04.as.aufgabe02;


/**
 * Thrown at insert()-operation when PriorityQueue is already full.
 */
public class FullPriorityQueueException extends Exception {
  
  private static final long serialVersionUID = 1L;

  public FullPriorityQueueException() {
    super();
  }
}
