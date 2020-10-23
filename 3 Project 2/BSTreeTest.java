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
     * tests the insert method
     */
    public void testInsert() {
        assertEquals(t.size(), 1);
        t.insert(k1, "one");
        assertEquals(t.size(), 2);
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

    }


    /**
     * tests the find method
     */
    public void testFind() {
        assertEquals(t.find(k1), "one");
        assertNull(t.find(k2));
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
