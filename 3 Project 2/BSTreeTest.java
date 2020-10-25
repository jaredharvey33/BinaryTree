// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98), Jared Harvey (jharvey33)

/**
 * @author Ren Robinson (rarobin98), Jared Harvey (jharvey33)
 * @version 2020.10.20
 *
 */
public class BSTreeTest extends student.TestCase {

    private KeyVector<String, String, String> k1;
    private KeyVector<String, String, String> k2;

    private BSTree<KeyVector<String, String, String>, String> t;
    private BSTree<KeyVector<String, String, String>, String> t2;
    private BSTNode<KeyVector<String, String, String>, String> n1;
    private BSTNode<KeyVector<String, String, String>, String> n2;

    // Integer Trees
    private KeyVector<Integer, Integer, Integer> k11;
    private KeyVector<Integer, Integer, Integer> k22;
    private KeyVector<Integer, Integer, Integer> k33;
    private BSTree<KeyVector<Integer, Integer, Integer>, Integer> t11;
    private BSTree<KeyVector<Integer, Integer, Integer>, Integer> t22;
    private BSTree<KeyVector<Integer, Integer, Integer>, Integer> t33;
    private BSTree<KeyVector<Integer, Integer, Integer>, Integer> tI;

    /**
     * sets up the tests classes.
     */

    public void setUp() {
        t = new BSTree<>();
        t2 = new BSTree<>();
        k1 = new KeyVector<>("one", "two", "three");
        k2 = new KeyVector<>("two", "two", "three");
        n1 = new BSTNode<>(k1, "one");
        n2 = new BSTNode<>(k1, "two");
        t.setRoot(n1);
        t.insert(k1, "one");

        k11 = new KeyVector<>(1, 1, 1);
        k22 = new KeyVector<>(2, 2, 2);
        k33 = new KeyVector<>(3, 3, 3);
        t33 = new BSTree<>();
        t33.setRoot(new BSTNode<>(k22, 2));
        tI = new BSTree<>();

    }


    /**
     * tests the insert method
     */
    public void testInsert() {
        tI.insert(k22, 2);
        assertEquals(tI.size(), 1);
        tI.insert(k11, 1);
        assertEquals(tI.size(), 2);
        tI.insert(k33, 3);
        assertEquals(tI.size(), 3);
    }


    /**
     * tests the insertIntDesc method
     */
    public void testInsertIntDesc() {
        tI.insertIntDesc(k22, 2);
        assertEquals(tI.size(), 1);
        tI.insertIntDesc(k11, 1);
        assertEquals(tI.size(), 2);
        tI.insertIntDesc(k33, 3);
        assertEquals(tI.size(), 3);
    }


    /**
     * tests the remove method
     */
    public void testRemove() {
        assertEquals(t.size(), 1);
        t.insert(k1, "one");
        assertEquals(t.size(), 2);
        t.remove(k1);
        assertEquals(t.size(), 1);
        assertNull(t.remove(k2)); // null
        assertEquals(t.size(), 1);

        t33.insert(k11, 1); // left
        t33.insert(k33, 3); // right
        // 2 remove right
        assertEquals((int)t33.remove(k33), 3);
        // 1 remove left
        assertEquals((int)t33.remove(k11), 1);
        // reset
        t33.insert(k11, 1); // left
        t33.insert(k33, 3); // right
        // 2 remove left
        assertEquals((int)t33.remove(k11), 1);
        // 1 remove right
        assertEquals((int)t33.remove(k33), 3);

        assertEquals(t33.getRoot().key(), k22);

        // 2 remove root
        assertEquals((int)t33.remove(k22), 2);

    }


    /**
     * tests the getRoot method
     */
    public void testGetRoot() {
        assertEquals(t.getRoot(), n1);

    }


    /**
     * tests the setRoot method
     */
    public void testSetRoot() {
        assertEquals(t.getRoot(), n1);
        t.setRoot(n2);
        assertEquals(t.getRoot(), n2);
    }


    /**
     * tests the clear method
     */
    public void testClear() {
        assertEquals(t.size(), 1);
        t.clear();
        assertEquals(t.size(), 0);

    }


    /**
     * tests the find method
     */
    public void testFind() {
        assertEquals(t.find(k1), "one");
        assertNull(t.find(k2));
        t.insert(k2, "two");
        assertEquals(t.find(k2), "two");

    }


    /**
     * tests the size method
     */
    public void testSize() {
        assertEquals(t2.size(), 0);
        assertEquals(t.size(), 1);
        t.insert(k1, "one");
        assertEquals(t.size(), 2);
    }

}
