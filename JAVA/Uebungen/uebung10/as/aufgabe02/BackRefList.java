/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Mon Nov 18 14:30:09 CET 2024
 */

package uebung10.as.aufgabe02;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BackRefList<T extends BackRefNodeItem<?>> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  protected BackRefList() {
    head = new Node<>(null, null, null);
    tail = new Node<>(null, head, null);
    head.setNext(tail);
  }
  
  protected Node<T> getHead() {
    return head;
  }
  
  protected Node<T> getTail() {
    return tail;
  }

  protected int size() {
    return size;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected Node<T> insert(T item) {
    Node<T> cursor = head.getNext();
    while ((cursor != tail) && (((Comparable) item.getElement())
        .compareTo(cursor.getItem().getElement())) > 0) {
      cursor = cursor.getNext();
    }
    return insertBefore(cursor, item);
  }

  protected Node<T> insertBefore(Node<T> node, T item) {
    Node<T> prev = node.getPrev();
    Node<T> newNode = new Node<>(item, prev, node);
    node.setPrev(newNode);
    prev.setNext(newNode);
    size++;
    return newNode;
  }

  protected T remove(Node<T> incNode) {
    incNode.getPrev().setNext(incNode.getNext());
    incNode.getNext().setPrev(incNode.getPrev());
    incNode.setNext(null);
    incNode.setPrev(null);
    size--;
    return incNode.getItem();
  }

  protected List<T> getItems() {
    List<T> list = new LinkedList<>();
    Node<T> cursor = head.getNext();
    while (cursor != tail) {
      list.add(cursor.getItem());
      cursor = cursor.getNext();
    }
    return list;
  }

  protected void printDiagnostic(String prefix, Map<Integer, String> objIdMap) {
    head.printDiagnostic(prefix, objIdMap);
    Node<T> cursor = head.getNext();
    while (cursor != tail) {
      cursor.printDiagnostic(prefix, objIdMap);
      cursor = cursor.getNext();
    }
    tail.printDiagnostic(prefix, objIdMap);
  }

}
