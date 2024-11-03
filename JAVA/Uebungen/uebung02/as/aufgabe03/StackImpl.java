/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Sun Sep 22 16:04:12 CEST 2024
 */

package uebung02.as.aufgabe03;


public class StackImpl<T> implements Stack<T> {
  
  /**
   * Nodes of a simple linked list.
   */
  private class Node<E> {

    private E element;
    private Node<E> next;

    /**
     * Constructs a new unlinked node.
     * 
     * @param elem
     *          Element for the node.
     */
    public Node(E elem) {
      element = elem;
      next = null;
    }

    /**
     * Adds the node next to this node.
     * 
     * @param next
     *          The next node.
     */
    public void appendNode(Node<E> next) {
      this.next = next;
    }

    public Node<E> getNext() {
      return next;
    }

    public E getElement() {
      return element;
    }
  }

  private Node<T> top;
  
  private int size;

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public T top() throws EmptyStackException {
    if (size == 0) {
    	throw new EmptyStackException("Could not get top of stack because stack is empty.");
    }
    return top.getElement();
  }

  @Override
  public void push(T element) {
    Node<T> newNode = new Node<> (element);
    newNode.appendNode(top);
    top = newNode;
    size++;
  }

  @Override
  public T pop() throws EmptyStackException {
	  if (size == 0) {
	    	throw new EmptyStackException("Could not remove top of stack because stack is empty.");
	  }
	  Node<T> topNode = top;
	  top = topNode.getNext();
	  size--;
	  return topNode.getElement();
  }
  
  @Override
  public void print() {
    System.out.println("Stack: (" + toString(top, "") + ")");
  }

  private String toString(Node<T> node, String content) {
    if (node == null) {
      return content;
    }
    if (!content.equals("")) {
      content += ", ";
    }
    content += node.getElement();
    return toString(node.getNext(), content);
  }
  

  public static void main(String[] args) {
    Stack<Integer> stack = new StackImpl<>();
    stack.print();
    final int TEST_SIZE = 4;
    for (int i = 0; i < TEST_SIZE; i++) {
      stack.push(i);
      stack.print();
      if (stack.size() != i+1) {
        System.out.println("ERROR: Stack.size() != " + (i+1));
        return;
      }
      if (stack.top() != i) {
        System.out.println("ERROR: Stack.top() != " + i);
        return;
      }
    }
    for (int i = TEST_SIZE-1; i > 0; i--) {
      if (stack.pop() != i) {
        System.out.println("ERROR: Stack.pop() != " + i);
        return;
      }
      stack.print();
      if (stack.size() != i) {
        System.out.println("ERROR: Stack.size() != " + i);
        return;
      }
      if (stack.top() != i-1) {
        System.out.println("ERROR: Stack.top() != " + (i-1));
        return;
      }
    }
    if (stack.pop() != 0) {
      System.out.println("ERROR: Stack.pop() != 0");
      return;
    }
    stack.print();
    if (!stack.isEmpty()) {
      System.out.println("ERROR: Stack.empty() != true");
      return;
    }
    if (stack.size() != 0) {
      System.out.println("ERROR: Stack.size() != 0");
      return;
    }
    try {
      stack.top();
      System.out.println("ERROR: no EmptyStackException for stack.top()!");
      return;
    } 
    catch(EmptyStackException e) {
      e = null;
    }
    try {
      stack.pop();
      System.out.println("ERROR: no EmptyStackException for stack.pop()!");
      return;
    } 
    catch(EmptyStackException e) {
      e = null;
    }
  }
  
}


/* Session-Log:

Stack: ()
Stack: (0)
Stack: (1, 0)
Stack: (2, 1, 0)
Stack: (3, 2, 1, 0)
Stack: (2, 1, 0)
Stack: (1, 0)
Stack: (0)
Stack: ()

*/
 
