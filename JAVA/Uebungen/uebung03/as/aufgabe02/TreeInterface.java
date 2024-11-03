/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Mon Sep 30 18:01:29 CEST 2024
 */

package uebung03.as.aufgabe02;

public interface TreeInterface<T> {

  T root();

  void setRoot(T root);

  T parent(T child) throws NoSuchNodeException;

  T leftChild(T parent) throws NoSuchNodeException;

  T rightChild(T parent) throws NoSuchNodeException;

  boolean isInternal(T node) throws NoSuchNodeException;

  boolean isExternal(T node) throws NoSuchNodeException;

  boolean isRoot(T node); 

  void setRightChild(T parent, T child) throws NoSuchNodeException;

  void setLeftChild(T parent, T child) throws NoSuchNodeException;

  void removeRightChild(T parent) throws NoSuchNodeException;

  void removeLeftChild(T parent) throws NoSuchNodeException;
  
  int size();
  
}
 
