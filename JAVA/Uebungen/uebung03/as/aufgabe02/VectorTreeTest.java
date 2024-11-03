/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Mon Sep 30 18:01:29 CEST 2024
 */

package uebung03.as.aufgabe02;


public class VectorTreeTest {
  
  public static void main(String[] args) throws NoSuchNodeException {

    VectorTree<Character> vt = new VectorTree<>();
    
    vt.printVector("Empty tree:");
    
    if (vt.size() != 0) {
      throw new Error("Bad size: " + vt.size() + " != 0");
    }
    if (vt.root() != null) {
      throw new Error("vt.root() != null");
    }

    Character a = 'A';
    vt.setRoot(a);
    vt.printVector("Setting root with 'A':");
    if (vt.size() != 1) {
      throw new Error("Bad size: " + vt.size() + " != 1");
    }
    if (!vt.isRoot(a)) {
      throw new Error("!vt.root(a)");
    }
    if (!vt.root().equals(a)) {
      throw new Error("!vt.root().equals(a) : " + vt.root());
    }
    if (!vt.isExternal(a)) {
      throw new Error("!vt.isExternal(a)");
    }
    if (vt.parent(a) != null) {
      throw new Error("vt.parent(a) != null");
    }

    Character d = 'D';
    vt.setRightChild(vt.root(), d);
    vt.printVector("Setting right child of 'A' with 'D':");
    if (vt.size() != 2) {
      throw new Error("Bad size: " + vt.size() + " != 2");
    }
    if (!vt.rightChild(vt.root()).equals(d)) {
      throw new Error("!vt.rightChild(vt.root()).equals(d) : "
          + vt.rightChild(vt.root()));
    }
    if (!vt.isExternal(d)) {
      throw new Error("!vt.isExternal(d)");
    }
    if (!vt.isInternal(vt.root())) {
      throw new Error("!vt.isInternal(vt.root()");
    }
    if (!vt.parent(d).equals(a)) {
      throw new Error("!vt.parent(d).equals(a)");
    }

    Character b = 'B';
    vt.setLeftChild(vt.root(), b);
    vt.printVector("Setting left child of 'A' with 'B':");
    if (vt.size() != 3) {
      throw new Error("Bad size: " + vt.size() + " != 3");
    }
    
     
    Character f = 'F';
    vt.setRightChild(b, f);
    vt.printVector("Setting right child of 'B' with 'F':");

    Character h = 'H';
    vt.setRightChild(f, h);
    vt.printVector("Setting right child of 'F' with 'H':");

    Character g = 'G';
    vt.setLeftChild(f, g);
    vt.printVector("Setting left child of 'F' with 'G':");
    if (vt.size() != 6) {
      throw new Error("Bad size: " + vt.size() + " != 6");
    }
    if (!vt.isInternal(f)) {
      throw new Error("!vt.isInternal(f)");
    }
    if (!vt.isExternal(h)) {
      throw new Error("!vt.isExternal(h)");
    }
    if (!vt.rightChild(vt.rightChild(vt.leftChild(vt.root()))).equals(h)) {
      throw new Error(
          "!vt.rightChild(vt.rightChild(vt.leftChild(vt.root()))).equals(h)");
    }

    vt.removeLeftChild(b);
    if (vt.size() != 6) {
      throw new Error("Bad size: " + vt.size() + " != 6");
    }

    vt.removeRightChild(b);
    vt.printVector("Removing right child of 'B':");
    if (vt.size() != 3) {
      throw new Error("Bad size: " + vt.size() + " != 3");
    }
    if (!vt.isExternal(b)) {
      throw new Error("!vt.isExternal(b)");
    }

    vt.setRightChild(d, 'J');
    vt.printVector("Setting right child of 'D' with 'J':");

    vt.setRightChild(a, 'X');
    vt.printVector("Setting right child of root 'A' with 'X':");
    if (vt.size() != 3) {
      throw new Error("Bad size: " + vt.size() + " != 3");
    }

    vt.setRoot('Y');
    vt.printVector("Setting root with 'Y':");
    if (vt.size() != 1) {
      throw new Error("Bad size: " + vt.size() + " != 1");
    }

    System.out.print("\nTesting if root is external: ");
    if (!vt.isExternal(vt.root())) {
      throw new Error("!vt.isExternal(vt.root())");
    }
    System.out.println("o.k.");

    System.out.print("\nAsking for node which does not exist: ");
    Character rightChild = vt.rightChild('Y');
    if (rightChild != null) {
      throw new Error("rightChild != null");
    }
    System.out.println("o.k.");
    
   
    System.out.print("\nUsing node which does not exist: ");
    NoSuchNodeException noSuchNodeException = null;
    try {
      vt.setRightChild('A', 'B');
    } catch (NoSuchNodeException e) {
      noSuchNodeException = e;
    }
    if (noSuchNodeException == null) {
      throw new Error("NoSuchNodeException missing!");
    }
    System.out.println("o.k.");

  }

}


/* Session-Log:

Empty tree:
[null, null]

Setting root with 'A':
[null, A]

Setting right child of 'A' with 'D':
[null, A, null, D]

Setting left child of 'A' with 'B':
[null, A, B, D]

Setting right child of 'B' with 'F':
[null, A, B, D, null, F, null, null]

Setting right child of 'F' with 'H':
[null, A, B, D, null, F, null, null, null, null, null, H, null, null, null, null]

Setting left child of 'F' with 'G':
[null, A, B, D, null, F, null, null, null, null, G, H, null, null, null, null]

Removing right child of 'B':
[null, A, B, D, null, null, null, null, null, null, null, null, null, null, null, null]

Setting right child of 'D' with 'J':
[null, A, B, D, null, null, null, J, null, null, null, null, null, null, null, null]

Setting right child of root 'A' with 'X':
[null, A, B, X, null, null, null, null, null, null, null, null, null, null, null, null]

Setting root with 'Y':
[null, Y, null, null, null, null, null, null, null, null, null, null, null, null, null, null]

Testing if root is external: o.k.

Asking for node which does not exist: o.k.

Using node which does not exist: o.k.

*/
