// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction

/**
 * @author Ren Robinson (rarobin98), Jared Harvey (jharvey33)
 * @version 2020.11.23
 *
 */
public class EntryTest extends student.TestCase {

    private Entry e1;
    private Handle<Pair> h1;
    private Handle<Pair> h2;
    private Pair p1;
    private Pair p2;

    /**
     * sets up the tests classes.
     */
    public void setUp() {
        p1 = new Pair(1, 1);
        p2 = new Pair(2, 2);
        h1 = new Handle<Pair>(p1, p2);
        h1 = new Handle<Pair>(p2, p1);
        Entry eN = new Entry();
        e1 = new Entry(h1);

    }// end setUp


    /**
     * Tests the getFlag method
     */
    public void testGetFlag() {
        assertEquals(e1.getFlag(), 1);
    }


    /**
     * Tests the setFlag method
     */
    public void testSetFlag() {
        assertEquals(e1.getFlag(), 1);
        e1.setFlag((short)2);
        assertEquals(e1.getFlag(), 2);

    }


    /**
     * Tests the getHandle method
     */
    public void testGetHandle() {
        assertEquals(e1.getHandle(), h1);
    }


    /**
     * Tests the setHandle method
     */
    public void testSetHandle() {
        assertEquals(e1.getHandle(), h1);
        e1.setHandle(h2);
        assertEquals(e1.getHandle(), h2);

    }

}
