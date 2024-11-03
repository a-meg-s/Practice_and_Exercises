/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Sun Oct  6 16:28:17 CEST 2024
 */

package uebung04.as.aufgabe02;

/**
 * A heap-based (array-implementation) Priority-Queue with fixed length.
 */
public class PriorityQueue<K extends Comparable<? super K>, V> {

  protected PQEntry<K, V>[] heapArray;

  /** Points to the last element in the heap. */
  protected int last = 0;

  public static class PQEntry<K extends Comparable<? super K>, V> implements
      Entry<K, V>, Comparable<PQEntry<K, V>> {

    protected K key;
    protected V value;

    protected PQEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public int compareTo(PQEntry<K, V> other) {
      return this.key.compareTo(other.key);
    }

    @Override
    public String toString() {
      return "(" + key + "," + value + ")";
    }

  }

  @SuppressWarnings("unchecked")
  public PriorityQueue(int maxSize) {
    heapArray = new PQEntry[maxSize + 1];
  }

  public Entry<K, V> insert(K key, V value) throws FullPriorityQueueException {

    if (last >= heapArray.length - 1) {
      throw new FullPriorityQueueException();
    }

    last++;
    PQEntry<K, V> e = new PQEntry<>(key, value);
    heapArray[last] = e;

    // Restore heap property by moving up
    upheap(last);

    return e;
  }

  public Entry<K, V> min() {
    if (isEmpty()) {
      return null;
    }

    return heapArray[1]; // Root element has the minimum key in a min-heap
  }

  public Entry<K, V> removeMin() {
    if (isEmpty()) {
      return null;
    }

    PQEntry<K, V> min = heapArray[1]; // root element
    heapArray[1] = heapArray[last]; // move last element to root
    heapArray[last] = null; // remove last element
    last--;

    // restore heap property by moving down
    downheap(1);

    return min;
  }

  protected void upheap(int currentIndex) {
    while (currentIndex > 1) {
      int parentIndex = currentIndex / 2;
      if (heapArray[currentIndex].compareTo(heapArray[parentIndex]) >= 0) {
        break; // heap property is satisfied
      }
      swap(parentIndex, currentIndex);
      currentIndex = parentIndex;
    }
  }

  protected void downheap(int currentIndex) {
    while (true) {
      int leftChild = 2 * currentIndex;
      int rightChild = leftChild + 1;
      int smallest = currentIndex;

      // check left child
      if (leftChild <= last && heapArray[leftChild].compareTo(heapArray[smallest]) < 0) {
        smallest = leftChild;
      }

      // check right child
      if (rightChild <= last && heapArray[rightChild].compareTo(heapArray[smallest]) < 0) {
        smallest = rightChild;
      }

      // if current node is in the correct position, break
      if (smallest == currentIndex) {
        break;
      }

      // swap with the smallest child and continue down
      swap(currentIndex, smallest);
      currentIndex = smallest;
    }
  }

  /**
   * Swaps a child-node with its parent-node.
   * 
   * @param parentIndex Index of the parent-node.
   * @param childIndex  Index of the child-node.
   */
  protected void swap(int parentIndex, int childIndex) {
    PQEntry<K, V> temp = heapArray[parentIndex];
    heapArray[parentIndex] = heapArray[childIndex];
    heapArray[childIndex] = temp;
  }

  public boolean isEmpty() {
    return last == 0;
  }

  public int size() {
    return last;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < heapArray.length; i++) {
      PQEntry<K, V> e = heapArray[i];
      if (e != null) {
        sb.append('[');
        sb.append(e);
        sb.append(',');
        sb.append(i);
        sb.append(']');
      } else {
        sb.append("null");
      }
      if (i < heapArray.length - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  public void print() {
    System.out.println(toString());
  }

}
