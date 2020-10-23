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
public class BSTNodeTest extends student.TestCase {

    private BSTNode<String, Integer> nu;
    private BSTNode<String, Integer> n1;
    private BSTNode<String, Integer> n2;
    private BSTNode<String, Integer> n3;
    private BSTNode<String, Integer> n4;
    private BSTNode<String, Integer> n5;
    private BSTNode<String, Integer> n6;

    /**
     * sets up the tests classes.
     */

    public void setUp() {

        nu = new BSTNode<>();
        n1 = new BSTNode<>("test", 20);
        n2 = new BSTNode<>("test2", 30);
        n3 = new BSTNode<>("test3", 40);
        n4 = new BSTNode<>("test4", 50);

        n5 = new BSTNode<>("test4", 70);
        n5.setLeft(n3);
        n5.setRight(n2);
        n2.setRight(n1);

        n6 = new BSTNode<>("test4", 80);

    }// end setUp


    /**
     * tests the Key method
     */
    public void testKey() {
        assertEquals(n1.key(), "test");
        assertEquals(nu.key(), null);
    }


    /**
     * tests the setKey method
     */
    public void testSetKey() {
        assertEquals(n1.key(), "test");
        n1.setKey("test1");
        assertEquals(n1.key(), "test1");
    }


    /**
     * tests the Element method
     */
    public void testElement() {
        assertEquals((int)n1.element(), 20);

    }


    /**
     * tests the setElement method
     */
    public void testSetElement() {
        assertEquals((int)n1.element(), 20);
        n1.setElement(50);
        assertEquals((int)n1.element(), 50);
    }


    /**
     * tests the Left method
     */
    public void testLeft() {
        assertEquals(n5.left(), n3);

    }


    /**
     * tests the SetLeft method
     */
    public void tesSetLeft() {
        assertEquals(n5.left(), n3);
        n5.setLeft(n2);
        assertEquals(n5.left(), n2);

    }


    /**
     * tests the Right method
     */
    public void testright() {
        assertEquals(n5.right(), n2);

    }


    /**
     * tests the SetRight method
     */
    public void tesSetRight() {
        assertEquals(n5.right(), n2);
        n5.setRight(n3);
        assertEquals(n5.right(), n3);
    }


    /**
     * tests the isLeaf method
     */
    public void testisLeaf() {
        assertTrue(n1.isLeaf());
        n1.setLeft(n2);
        assertFalse(n1.isLeaf());
        n3.setRight(n1);
        assertFalse(n3.isLeaf());

    }


    /**
     * tests the getHeight method
     */
    public void testGetHeight() {
        assertEquals(n4.getHeight(), 0);
        n4.setLeft(n3);
        assertEquals(n4.getHeight(), 1);
        n3.setLeft(n2);
        assertEquals(n4.getHeight(), 3);
        n2.setLeft(n1);
        assertEquals(n4.getHeight(), 3);

        assertEquals(n5.getHeight(), 3);
        n5.setRight(n1);

        n6.setRight(n4);
        assertEquals(n6.getHeight(), 4);

    }


    /**
     * tests the getLevel method
     */
    public void testGetLevel() {
        assertEquals(n1.getLevel(n1, n1.element(), 0), 0);

        assertEquals(n3.getLevel(n5, n3.element(), 0), 1);
        assertEquals(n1.getLevel(n5, n1.element(), 0), 2);

    }

}
