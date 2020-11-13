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
 * Tests the Record class
 */
public class RecordTest extends student.TestCase {

    private byte[] id1;
    private byte[] key1;
    private int flag1;
    private Record r1;
    private Record r1Copy;
    private Record r1L;
    private byte[] keyL;
    private Record r1f;
    int[] checkId = new int[4];
    int[] checkKey = new int[4];
    byte[] newid = new byte[4];
    byte[] newkey = new byte[4];

    /**
     * sets up the tests classes.
     */
    public void setUp() {
        id1 = new byte[] { 1, 1, 1, 1 };
        key1 = new byte[] { 1, 0, 0, 0 };
        keyL = new byte[] { 0, 0, 0, 0 };
        flag1 = 1;
        r1 = new Record(id1, key1);
        r1Copy = new Record(id1, key1);
        r1L = new Record(id1, keyL);
        r1f = new Record(id1, key1, flag1);
        checkId = new int[] { 1, 1, 1, 1 };
        checkKey = new int[] { 1, 0, 0, 0 };
        newid = new byte[] { 2, 2, 2, 2 };
        newkey = new byte[] { 2, 2, 2, 2 };

    }


    /*
     * Tests the getInt method
     */
    public void testGetInt() {
        assertEquals(r1.getInt(), 16843009);
    }


    /*
     * Tests the getFloat method
     */
    public void testGetFloat() {
        assertEquals(r1.getFloat(), 2.350988701644575E-38, 0);
    }


    /*
     * Tests the getFlag method
     */
    public void testGetFlag() {
        assertEquals(r1f.getFlag(), 1);
    }


    /*
     * Tests the setFlag method
     */
    public void testSetFlag() {
        assertEquals(r1f.getFlag(), 1);
        r1f.setFlag(2);
        assertEquals(r1f.getFlag(), 2);
    }


    /*
     * Tests the getID method
     */
    public void testGetId() {
        for (int i = 0; i < r1.getId().length; i++) {
            assertEquals(r1.getId()[i], checkId[i]);
        }
    }


    /*
     * Tests the setID method
     */
    public void testsetId() {
        r1.setId(newid);
        for (int i = 0; i < r1.getId().length; i++) {
            assertEquals(r1.getId()[i], newid[i]);
        }
    }


    /*
     * Tests the getKey method
     */
    public void testGetKey() {
        for (int i = 0; i < r1.getId().length; i++) {
            assertEquals(r1.getKey()[i], checkKey[i]);
        }
    }


    /*
     * Tests the setKey method
     */
    public void testsetKey() {
        r1.setId(newkey);
        for (int i = 0; i < r1.getId().length; i++) {
            assertEquals(r1.getId()[i], newkey[i]);
        }
    }


    /*
     * Tests the compareTo method
     */
    public void testCompareTo() {
        // 1
        assertEquals(r1.compareTo(r1L), 1);
        // -1
        assertEquals(r1L.compareTo(r1), -1);
        // 0
        assertEquals(r1.compareTo(r1Copy), 0);

    }

}
