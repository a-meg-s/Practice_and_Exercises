package uebung04.as.aufgabe01;

public class MinHeap {
    public int[] heap; // Array to hold the heap elements
    private int size; // Current num of elements in the heap
    private int capacity; // Max capacity of the heap

    // Constructor to initialize the heap with a specific capacity
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Helper methods to get parent and children indices
    private int getParent(int i) {
        return (i - 1) / 2;
    }

    private int getLeftChild(int i) {
        return 2 * i + 1;
    }

    private int getRightChild(int i) {
        return 2 * i + 2;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // method to insert a new element
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full.");
            return;
        }

        // Step 1: Insert new element at the end of heap
        heap[size] = value;
        int current = size;
        size++;

        // Step 2: Bubble up to maintain the min-heap property
        while (current > 0 && heap[current] < heap[getParent(current)]) {
            // Swap with parent
            int temp = heap[current];
            heap[current] = heap[getParent(current)];
            heap[getParent(current)] = temp;

            // move up to parent's index
            current = getParent(current);
        }

        // print heap after each insert for visualisation
        printHeap();
    }

    // method to remove the min element (root)
    public int removeMin() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return -1;
        }

        // Step 1: Remove the minimum element (root)
        int min = heap[0];

        // Step 2: Replace root with last element in the heap
        heap[0] = heap[size - 1];
        size--;

        // Step 3: Bubble down to restore the min-heap property
        int current = 0;
        while (true) {
            int leftChild = getLeftChild(current);
            int rightChild = getRightChild(current);
            int smallest = current;

            // Find the smallest of the current node and its children
            if (leftChild < size && heap[leftChild] < heap[smallest]) {
                smallest = leftChild;
            }

            if (rightChild < size && heap[rightChild] < heap[smallest]) {
                smallest = rightChild;
            }

            // if smallest is still the current node, we're done
            if (smallest == current) {
                break;
            }

            // Otherwise swap with the smallest child and continue
            int temp = heap[current];
            heap[current] = heap[smallest];
            heap[smallest] = temp;

            // move to next position
            current = smallest;
        }

        // print heap after each remove for visualisation
        printHeap();

        return min;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10); // heap w capacity of 10

        // insert elements in given sequence
        minHeap.insert(4);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(2);
        minHeap.insert(1);

        // Perform removeMin() operations until the heap is empty
        System.out.println("Removing elements:");
        while (minHeap.size > 0) {
            minHeap.removeMin();
        }

    }
}