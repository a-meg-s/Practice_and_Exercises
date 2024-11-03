package uebung02.as.aufgabe02;


public class Node<E> {

  private E element;
  private Node<E> next, prev;

  Node() {
    this(null, null, null);
  }

  Node(E e, Node<E> p, Node<E> n) {
    element = e;
    next = n;
    prev = p;
  }

  void setElement(E newElem) {
    element = newElem;
  }

  void setNext(Node<E> newNext) {
    next = newNext;
  }

  void setPrev(Node<E> newPrev) {
    prev = newPrev;
  }

  E getElement() {
    return element;
  }

  Node<E> getNext() {
    return next;
  }

  Node<E> getPrev() {
    return prev;
  }
}
 
 
 
