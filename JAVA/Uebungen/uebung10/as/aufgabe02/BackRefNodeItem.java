/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Mon Nov 18 14:30:09 CET 2024
 */

package uebung10.as.aufgabe02;

import java.util.Map;

abstract class BackRefNodeItem<T extends Comparable<T>> implements Item<T> {

  private T element;
  /**
   * By convention: (backReference == null) is a <defunct> node (not an item of
   * the graph anymore)!
   */
  private Node<?> backReference;

  protected void setBackReference(Node<?> node) {
    backReference = node;
  }
  
  protected Node<?> getBackReference() {
    return backReference;
  }
  
  protected T setElement(T element) {
    T old = this.element;
    this.element = element;
    return old;
  }

  @Override
  public T getElement() {
    return element;
  }

  @Override
  public String toString() {
    return element.toString();
  }

}

class Node<T extends BackRefNodeItem<?>> {

  private T item;
  private Node<T> prev, next;

  protected Node(T item, Node<T> prev, Node<T> next) {
    this.item = item;
    this.prev = prev;
    this.next = next;
  }

  protected void setItem(T item) {
    this.item = item;
  }

  protected T getItem() {
    return item;
  }

  protected void setNext(Node<T> next) {
    this.next = next;
  }

  protected Node<T> getNext() {
    return next;
  }

  protected void setPrev(Node<T> prev) {
    this.prev = prev;
  }

  protected Node<T> getPrev() {
    return prev;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected void printDiagnostic(String prefix, Map<Integer, String> objIdMap) {
    String id = getID(this, objIdMap);
    String prevID = getID(prev, objIdMap);
    String nextID = getID(next, objIdMap);
    Node<T> itemBackRef = null;
    String itemBackRefID = null;
    if (item != null) {
      itemBackRef = (Node<T>) item.getBackReference();
      itemBackRefID = getID(itemBackRef, objIdMap);
    }
    System.out.print(prefix + "(" + id + "|" + item + "(" + itemBackRefID + ")|"
        + prevID + "<>" + nextID + ")");
    boolean newline = false;
    if (item instanceof Graph.VertexImpl) {
      System.out.println();
      newline = true;
      Graph.VertexImpl v = (Graph.VertexImpl) item;
      v.getIncidentList().printDiagnostic(prefix + "    ", objIdMap);
    }
    if (this.item instanceof Graph.EdgeImpl) {
      if (this.item.getBackReference() == this) {
        Graph.EdgeImpl edge = (Graph.EdgeImpl) this.item;
        String endpoint0ID = getID(edge.getEndpoints()[0], objIdMap);
        String endpoint1ID = getID(edge.getEndpoints()[1], objIdMap);
        System.out
            .print(" : endpoints[" + endpoint0ID + "," + endpoint1ID + "]");
        String backref0ID = getID(edge.getBackReferences()[0], objIdMap);
        String backref1ID = getID(edge.getBackReferences()[1], objIdMap);
        System.out.print(" : backrefs[" + backref0ID + "," + backref1ID + "]");
      }
    }
    if (!newline) {
      System.out.println();
    }
  }

  protected static String getID(Object obj, Map<Integer, String> objIdMap) {
    String id;
    if (obj != null) {
      id = objIdMap.get(obj.hashCode());
      id = (id != null) ? id : String.valueOf(obj.hashCode());
    } else {
      id = null;
    }
    return id;
  }

}
