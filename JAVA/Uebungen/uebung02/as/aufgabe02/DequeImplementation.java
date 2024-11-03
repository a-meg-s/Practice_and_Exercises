package uebung02.as.aufgabe02;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Double linked list and DE-Queue (double ended queue: Deque)
 */
public class DequeImplementation<E> {

    private Node<E> header, trailer;
    private int size;

    public DequeImplementation() {
        header = new Node<>();
        trailer = new Node<>();
        header.setNext(trailer);
        trailer.setPrev(header);
        size = 0;
    }

    public void insertFirst(E element) {
        Node<E> second = header.getNext();
        Node<E> first = new Node<>(element, header, second);
        second.setPrev(first);
        header.setNext(first);
        size++;
    }

    public E removeLast() throws DequeEmptyException {
        if (isEmpty()) {
            throw new DequeEmptyException("Deque is empty!");
        }
        Node<E> last = trailer.getPrev();
        E o = last.getElement();
        Node<E> secondtolast = last.getPrev();
        trailer.setPrev(secondtolast);
        secondtolast.setNext(trailer);
        size--;
        return o;
    }

    public E removeFirst() throws DequeEmptyException {
        if (isEmpty()) {
            throw new DequeEmptyException("Deque is empty!");
        }
        // Gets the current first value in the list
        Node<E> first = header.getNext();
        // o stores the first element of the array now
        E o = first.getElement();
        // second stores the next element after the first one
        Node<E> second = first.getNext();
        // sets the next element as the header
        header.setNext(second);
        // now the header which is the first element of the list is set so element 2 becomes 1, shifting all elements left
        second.setPrev(header);
        // Reduces the list size as there would be an empty slot in the list
        size--;
        // returns the new starting value
        return o;
    }

    public void insertLast(E element) {
        // Creates a new array names node, has 4 parts
        Node<E> node = new Node<>(element, trailer.getPrev(), trailer);
        // asks for the element before the cutoff point "trailer" and sets the next element at the end of it
        trailer.getPrev().setNext(node);
        // sets the new array into 'node'
        trailer.setPrev(node);
        // increases size by 1
        size++;
    }

    public E first() throws DequeEmptyException {
        if (isEmpty()) {
            throw new DequeEmptyException("Deque is empty!");
        }
        return header.getNext().getElement();
    }

    public E last() throws DequeEmptyException {
        if (isEmpty()) {
            throw new DequeEmptyException("Deque is empty!");
        }
        return trailer.getPrev().getElement();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        LinkedList<String> elements = new LinkedList<>();
        Node<E> node = header.getNext();
        while (node != trailer) {
            elements.add(node.getElement().toString());
            node = node.getNext();
        }
        return elements.stream().collect(Collectors.joining(", ", "[", "]"));
    }

    public static void main(String[] args) {

        DequeImplementation<Integer> deque = new DequeImplementation<>();
        deque.insertFirst(3);
        deque.insertFirst(2);
        deque.insertFirst(1);
        System.out.println(deque);
        deque.insertLast(4);
        deque.insertLast(5);
        deque.insertLast(6);
        System.out.println(deque);
        System.out.println("size  : " + deque.size());
        System.out.println("empty : " + deque.isEmpty());
        System.out.println("first : " + deque.first());
        System.out.println("last  : " + deque.last());
        for (int i = 0; i < 3; i++) {
            System.out.print(deque.removeFirst() + " ");
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.print(deque.removeLast() + " ");
        }
        System.err.println();
        deque.removeFirst();
    }
}

/* Session-Log:

[1, 2, 3]
[1, 2, 3, 4, 5, 6]
size  : 6
empty : false
first : 1
last  : 6
1 2 3
6 5 4
Exception in thread "main" uebung02.ml.aufgabe02.DequeEmptyException: Deque is empty!
  at uebung02.ml.aufgabe02.DequeImplementation.removeFirst(DequeImplementation.java:50)
  at uebung02.ml.aufgabe02.DequeImplementation.main(DequeImplementation.java:125)

*/
