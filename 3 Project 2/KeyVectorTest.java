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

    private KeyVector<String, String, String> kv3;
    KeyVector<String, String, String> nu;
    KeyVector<String, String, String> kv2;

    // compareTo
    private KeyVector<Integer, Integer, Integer> k1;
    private KeyVector<Integer, Integer, Integer> k2;
    private KeyVector<Integer, Integer, Integer> k3;
    private KeyVector<Integer, Integer, Integer> k4;
    private KeyVector<Integer, Integer, Integer> k5;
    private KeyVector<Integer, Integer, Integer> kn;
    private KeyVector<Integer, Integer, Integer> kn2;
    private KeyVector<Integer, Integer, Integer> k6;
    private KeyVector<Integer, Integer, Integer> k7;
    //compareToDesc 
    private KeyVector<String, String, String> kS1;
    private KeyVector<String, String, String> kS2;
    private KeyVector<String, String, String> kS3;
    private KeyVector<String, String, String> kS4;
    private KeyVector<String, String, String> kS5;
    private KeyVector<String, String, String> kSn;
    private KeyVector<String, String, String> kSn2;
    private KeyVector<String, String, String> kS6;
    private KeyVector<String, String, String> kS7;

    /**
     * sets up the tests classes.
     */

    public void setUp() {
        nu = new KeyVector<>();
        kv2 = new KeyVector<String, String, String>("one", "two");
        kv3 = new KeyVector<String, String, String>("one", "two", "three");

        // compareTo
        k1 = new KeyVector<Integer, Integer, Integer>(1, 1, 1);
        new KeyVector<Integer, Integer, Integer>(1, 1, 1);

        k2 = new KeyVector<Integer, Integer, Integer>(2, 1, 1);
        k3 = new KeyVector<Integer, Integer, Integer>(0, 1, 1);

        k4 = new KeyVector<Integer, Integer, Integer>(1, 2, 1);
        k5 = new KeyVector<Integer, Integer, Integer>(1, 0, 1);

        // null key 3
        kn = new KeyVector<Integer, Integer, Integer>(1, 1, null);
        kn2 = new KeyVector<Integer, Integer, Integer>(1, 1, null);

        k6 = new KeyVector<Integer, Integer, Integer>(1, 1, 2);
        k7 = new KeyVector<Integer, Integer, Integer>(1, 1, 0);
        
        //compareToDesc
        kS1 = new KeyVector<String,String,String>("b","b","b");

        kS2 = new KeyVector<String,String,String>("c","b","b");
        kS3 = new KeyVector<String,String,String>("a","b","b");
        
        kS4 = new KeyVector<String,String,String>("b","c", "c");
        kS5 = new KeyVector<String,String,String>("b", "a", "b");

        // null key 3
        kSn = new KeyVector<String,String,String>("b","b", null);
        kSn2 = new KeyVector<String,String,String>("b", "b", null);

        kS6 = new KeyVector<String,String,String>("b","b","c");
        kS7 = new KeyVector<String,String,String>("b", "b", "a");

    }


    /**
     * tests the getKey methods
     */
    public void testGetKey() {
        assertNull(nu.getKey1());
        assertNull(kv2.getKey3());
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
     * tests the compareToIntDesc method
     */
    public void testCompareToIntDesc() {
        assertEquals(kS1.compareToIntDesc(kS1), 0);

        assertEquals(kS1.compareToIntDesc(kS3), -1);
        assertEquals(kS1.compareToIntDesc(kS2), 1);

        assertEquals(kS1.compareToIntDesc(kS5), -1);
        assertEquals(kS1.compareToIntDesc(kS4), 1);

        assertEquals(kSn.compareToIntDesc(kSn2), 0);

        assertEquals(kS1.compareToIntDesc(kS7), -1);
        assertEquals(kS1.compareToIntDesc(kS6), 1);
        
        
        assertEquals(kS1.compareToIntDesc(kS7), -1);
        assertEquals(kS1.compareToIntDesc(kS6), s1);
    }

}
