/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Sun Sep 22 16:04:12 CEST 2024
 */

package uebung02.as.aufgabe03;

/**
 * Interface for a stack: a collection of objects that are inserted and removed
 * according to the last-in first-out principle.
 * 
 * @author Roberto Tamassia
 * @author Michael Goodrich
 * @see EmptyStackException
 */

public interface Stack<T> {

  /**
   * Return the number of elements in the stack.
   * 
   * @return Number of elements in the stack.
   */
  public int size();

  /**
   * Return whether the stack is empty.
   * 
   * @return True if the stack is empty, false otherwise.
   */
  public boolean isEmpty();

  /**
   * Inspect the element at the top of the stack.
   * 
   * @return Top element in the stack.
   * @exception EmptyStackException
   *              If the stack is empty.
   */
  public T top() throws EmptyStackException;

  /**
   * Insert an element at the top of the stack.
   * 
   * @param element
   *          Element to be inserted.
   */
  public void push(T element);

  /**
   * Remove the top element from the stack.
   * 
   * @return Element removed.
   * @exception EmptyStackException
   *              If the stack is empty.
   */
  public T pop() throws EmptyStackException;
  
  /**
   * Prints the contents of the stack to the console. 
   */
  public void print();

}
 
 
 
