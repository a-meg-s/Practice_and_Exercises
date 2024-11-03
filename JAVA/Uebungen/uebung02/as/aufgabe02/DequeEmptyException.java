package uebung02.as.aufgabe02;

/**
 * Runtime exception thrown when one tries to perform operation on an empty
 * queue.
 */

public class DequeEmptyException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DequeEmptyException(String err) {
    super(err);
  }
} 
 
 
