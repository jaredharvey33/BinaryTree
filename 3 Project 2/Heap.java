
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.10.28
 *
 */
class Heap<K extends Comparable<K>> {
    private K[] heap; // Pointer to the heap array
    private int size; // Maximum size of the heap
    private int n; // Number of things now in heap

    // Constructor supporting preloading of heap contents
    public Heap(K[] h, int num, int max) {
        heap = h;
        n = num;
        size = max;
        buildheap();
    }


    // Function to print the contents of the heap
    public String toString() {
        String s = "";
        for (int i = 0; i < (n / 2); i++) {
            s = s.concat("Parent : " + heap[i] + "\n");
            if (leftchild(i) < n)
                s = s.concat(" Left : " + heap[leftchild(i)] + "\n");
            if (rightchild(i) < n)
                s = s.concat(" Right :" + heap[rightchild(i)] + "\n");
            s = s.concat("\n");
        }
        return s;
    }


    public K check() {
        if (n == 0) {
            return null;
        }
        return heap[0];
    }


    // Return current size of the heap
    public int heapsize() {
        return n;
    }


    // Return current size of the heap
    public void sizeBack(int back) {
        n = back;
    }


    // Return true if pos a leaf position, false otherwise
    public boolean isLeaf(int pos) {
        return (pos >= n / 2) && (pos < n);
    }


    // Return position for left child of pos
    public int leftchild(int pos) {
        return 2 * pos + 1;
    }


    // Return position for right child of pos
    public int rightchild(int pos) {
        return 2 * pos + 2;
    }


    // Return position for parent
    public int parent(int pos) {
        return (pos - 1) / 2;
    }


    public void insert(K key) {
        if (n >= size) {
            System.out.println("Heap is full");
            return;
        }
        heap[n] = key;
        int curr = n;

        while (heap[curr].compareTo(heap[parent(curr)]) < 0) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
        n++;
    }


    // Heapify contents of Heap
    public void buildheap() {
        for (int i = n / 2 - 1; i >= 0; i--)
            siftdown(i);
    }


    // Put element in its correct place
    public void siftdown(int pos) {
        if ((pos < 0) || (pos >= n))
            return; // Illegal position
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j < (n - 1)) && (heap[j].compareTo(heap[j + 1]) > 0))
                j++;
            if (heap[pos].compareTo(heap[j]) <= 0)
                return;
            swap(pos, j);
            pos = j;
        }
    }


    public void swap(int a, int b) {
        K temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }


    // Remove and return maximum value
    public K removeMin() {
        if (n == 0)
            return null; // Removing from empty heap
        swap(0, --n); // Swap maximum with last value
        siftdown(0); // Put new heap root val in correct place
        return heap[n];
    }

}
