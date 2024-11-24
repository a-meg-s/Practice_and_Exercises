/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Mon Nov 18 14:30:09 CET 2024
 */

package uebung10.as.aufgabe02;

interface Item<T extends Comparable<T>> {
  T getElement();
}


interface Vertex<V extends Comparable<V>> extends Item<V> {
  // Type Vertex
}


interface Edge<E extends Comparable<E>> extends Item<E> {
  // Type Edge
}


interface GraphInterface<V extends Comparable<V>, E extends Comparable<E>> {

  int numVertices();

  int numEdges();

  Iterable<Vertex<V>> vertices();

  Iterable<Edge<E>> edges();

  V replace(Vertex<V> v, V element) throws IllegalArgumentException;

  E replace(Edge<E> e, E element) throws IllegalArgumentException;

  Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws IllegalArgumentException;

  Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException;

  Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException;

  boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws IllegalArgumentException;

  Vertex<V> insertVertex(V element);

  Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E element) 
      throws IllegalArgumentException;

  V removeVertex(Vertex<V> v) throws IllegalArgumentException;

  E removeEdge(Edge<E> e) throws IllegalArgumentException;
}



