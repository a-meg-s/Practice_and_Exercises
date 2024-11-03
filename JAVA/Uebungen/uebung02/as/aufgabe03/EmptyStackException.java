/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Sun Sep 22 16:04:12 CEST 2024
 */

package uebung02.as.aufgabe03;

/**
 * Runtime exception thrown when one tries to perform operation top or pop on an
 * empty stack.
 */

public class EmptyStackException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public EmptyStackException(String err) {
    super(err);
  }
}
 
 
 
