/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Mon Sep 30 18:01:29 CEST 2024
 */

package uebung03.as.aufgabe02;

import java.util.ArrayList;

public class VectorTree<T> implements TreeInterface<T> {
  
  private final static int ROOT_INDEX = 1;

  protected ArrayList<T> binaryTree;
  protected int size;

  public VectorTree() {
    binaryTree = new ArrayList<>();
    binaryTree.add(0, null);
    binaryTree.add(ROOT_INDEX, null);
  }

  @Override
  public T root() {
    // TODO: Implement here...
    return null;
  }

  @Override
  public void setRoot(T root) {
    // TODO: Implement here...
  }

  @Override
  public T parent(T child) throws NoSuchNodeException {
    // TODO: Implement here...
    return null;
  }

  @Override
  public T leftChild(T parent) throws NoSuchNodeException {
    // TODO: Implement here...
    return null;
  }

  @Override
  public T rightChild(T parent) throws NoSuchNodeException {
    // TODO: Implement here...
    return null;
  }

  @Override
  public boolean isInternal(T node) throws NoSuchNodeException {
    // TODO: Implement here...
    return false;
  }

  @Override
  public boolean isExternal(T node) throws NoSuchNodeException {
    // TODO: Implement here...
    return false;
  }

  @Override
  public boolean isRoot(T node) {
    // TODO: Implement here...
    return false;
  }
  
    
  @Override
  public void setRightChild(T parent, T child) throws NoSuchNodeException {
    // TODO: Implement here...
  }

  @Override
  public void setLeftChild(T parent, T child) throws NoSuchNodeException {
    // TODO: Implement here...
  }

  @Override
  public void removeRightChild(T parent) throws NoSuchNodeException {
    // TODO: Implement here...
  }

  @Override
  public void removeLeftChild(T parent) throws NoSuchNodeException {
    // TODO: Implement here...
  }

  @Override
  public int size() {
    // TODO: Implement here...
    return -1;
  }
  
  public void printVector(String message) {
    System.out.println("\n" + message);
    System.out.println(binaryTree);
  }

}
