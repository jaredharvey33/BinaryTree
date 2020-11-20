// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98), Jared Harvey (jharvey33)
/*
 * @author Ren Robinson (rarobin98), Jared Harvey (jharvey33)
 * 
 * @version 2020.11.09
 * 
 * Tests the heap class
 */
public class HeapTest extends student.TestCase {

    private static final int MAX = 100;
    private Heap<String> h1;
    private Heap<String> hE;
    private Heap<String> h2;
    private Heap<String> h3;
    private Heap<String> h4;
    String[] check = new String[3];
    String[] check4 = new String[4];

    /**
     * sets up the tests classes.
     */
    public void setUp() {
        String[] let = new String[MAX];
        String[] let2 = new String[MAX];
        h1 = new Heap<>(let, 0, MAX);
        hE = new Heap<>(let, 0, MAX);
        h2 = new Heap<>(let, 0, 2);
        h3 = new Heap<>(let, 0, 3);
        h4 = new Heap<>(let2, 0, 4);
        h1.insert("B");
        h1.insert("A");
        h1.insert("D");
        check = new String[] { "A", "B", "D" };
        h4.insert("A");
        h4.insert("B");
        h4.insert("C");
        h4.insert("Z");
        check4 = new String[] { "A", "B", "C", "Z" };
    }


    /*
     * Tests the heapSize method
     */
    public void testHeapSize() {
        assertEquals(h1.heapsize(), 3);

    }


    /*
     * Tests the sizeBack method
     */
    public void testSizeBack() {
        assertEquals(h1.heapsize(), 3);
        h1.sizeBack(2);
        assertEquals(h1.heapsize(), 2);
    }


    /*
     * Tests the isLeaf method
     */
    public void testisLeaf() {
        assertTrue(h1.isLeaf(1));
        assertFalse(h1.isLeaf(3));
    }


    /*
     * Tests the leftChild method
     */
    public void testLeftChild() {
        assertEquals(h1.leftchild(2), 5);
        assertEquals(h1.leftchild(1), 3);
    }


    /*
     * Tests the rightChild method
     */
    public void testRightChild() {
        assertEquals(h1.rightchild(1), 4);
        assertEquals(h1.rightchild(2), 6);
    }


    /*
     * Tests the parent method
     */
    public void testParent() {
        assertEquals(h1.parent(1), 0);
        assertEquals(h1.parent(3), 1);
    }


    /*
     * Tests the insert method
     */
    public void testInsert() {
        assertEquals(h2.heapsize(), 0);
        h2.insert("A");
        assertEquals(h2.heapsize(), 1);
        assertTrue(h2.insert("B"));
        assertFalse(h2.insert("C"));
    }


    /*
     * tests build heap method
     */
    public void testBuildHeap() {

        for (int i = 0; i < h1.heapsize(); i++) {
            h1.buildheap();
            assertEquals(h1.getHeap()[i], check[i]);
        }

    }


    /*
     * Tests the check method
     */
    public void testCheck() {
        assertEquals(h1.check(), "A");
        assertNull(hE.check());

    }


    /*
     * Tests the swap method
     */
    public void testswap() {
        h3.insert("A");
        h3.insert("C");
        assertEquals(h3.getHeap()[0], "A");
        assertEquals(h3.getHeap()[1], "C");
        h3.swap(0, 1);
        assertEquals(h3.getHeap()[0], "C");
        assertEquals(h3.getHeap()[1], "A");

    }


    /*
     * Tests the removeMin method
     */
    public void testRemoveMin() {
        assertNull(hE.removeMin());
        assertEquals(h1.removeMin(), "A");

    }


    /*
     * Tests the getHeap method
     */
    public void testGetHeap() {
        for (int i = 0; i < h4.heapsize(); i++) {
            assertEquals(h4.getHeap()[i], check4[i]);
        }

    }

}
