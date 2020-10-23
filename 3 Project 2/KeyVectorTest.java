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
public class KeyVectorTest extends student.TestCase {

    private KeyVector<String, String, String> nu;
    private KeyVector<String, String, String> kv2;
    private KeyVector<String, String, String> kv3;

    // compareTo
    private KeyVector<Integer, Integer, Integer> k1;
    private KeyVector<Integer, Integer, Integer> k11;
    private KeyVector<Integer, Integer, Integer> k2;
    private KeyVector<Integer, Integer, Integer> k3;
    private KeyVector<Integer, Integer, Integer> k4;
    private KeyVector<Integer, Integer, Integer> k5;
    private KeyVector<Integer, Integer, Integer> kn;
    private KeyVector<Integer, Integer, Integer> kn2;
    private KeyVector<Integer, Integer, Integer> k6;
    private KeyVector<Integer, Integer, Integer> k7;

    /**
     * sets up the tests classes.
     */

    public void setUp() {
        nu = new KeyVector<>();
        kv2 = new KeyVector("one", "two", null);
        kv3 = new KeyVector("one", "two", "three");

        // compareTo
        k1 = new KeyVector(1, 1, 1);
        k11 = new KeyVector(1, 1, 1);

        k2 = new KeyVector(2, 1, 1);
        k3 = new KeyVector(0, 1, 1);

        k4 = new KeyVector(1, 2, 1);
        k5 = new KeyVector(1, 0, 1);

        // null key 3
        kn = new KeyVector(1, 1, null);
        kn2 = new KeyVector(1, 1, null);

        k6 = new KeyVector(1, 1, 2);
        k7 = new KeyVector(1, 1, 0);

    }


    /**
     * tests the getKey methods
     */
    public void testGetKey() {
        assertEquals(kv3.getKey1(), "one");
        assertEquals(kv3.getKey2(), "two");
        assertEquals(kv3.getKey3(), "three");

    }


    /**
     * tests the setKey methods
     */
    public void testSetKey() {
        assertEquals(kv3.getKey1(), "one");
        kv3.setKey1("two");
        assertEquals(kv3.getKey1(), "two");

        assertEquals(kv3.getKey2(), "two");
        kv3.setKey2("one");
        assertEquals(kv3.getKey2(), "one");

        assertEquals(kv3.getKey3(), "three");
        kv3.setKey3("two");
        assertEquals(kv3.getKey3(), "two");
    }


    /**
     * tests the compareTo method
     */
    public void testCompareTo() {
        assertEquals(k1.compareTo(k1), 0);

        assertEquals(k1.compareTo(k3), -1);
        assertEquals(k1.compareTo(k2), 1);

        assertEquals(k1.compareTo(k5), -1);
        assertEquals(k1.compareTo(k4), 1);

        assertEquals(kn.compareTo(kn2), 0);

        assertEquals(k1.compareTo(k7), -1);
        assertEquals(k1.compareTo(k6), 1);

    }


    /**
     * tests the compareToString method
     */
    public void testCompareToString() {
        assertEquals(k1.compareToString(k1), 0);

        assertEquals(k1.compareToString(k3), -1);
        assertEquals(k1.compareToString(k2), 1);

        assertEquals(k1.compareToString(k5), 1);
        assertEquals(k1.compareToString(k4), -1);

        assertEquals(kn.compareToString(kn2), 0);

        assertEquals(k1.compareToString(k7), -1);
        assertEquals(k1.compareToString(k6), 1);

    }

}