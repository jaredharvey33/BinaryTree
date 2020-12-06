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
public class PairTest extends student.TestCase {

    private Pair p1;

    /**
     * sets up the tests classes.
     */
    public void setUp() {
        p1 = new Pair(1, 1);

    }// end setUp


    /**
     * Tests the toString method
     */
    public void testToString() {
        assertEquals(p1.toString(), "Starting Byte Location: 1, Size 1 bytes");
    }


    /**
     * Tests the getLoc method
     */
    public void testGetLoc() {
        assertEquals(p1.getLoc(), 1);

    }


    /**
     * Tests the setLoc method
     */
    public void testSetLoc() {
        assertEquals(p1.getLoc(), 1);
        p1.setLoc(2);
        assertEquals(p1.getLoc(), 2);
    }


    /**
     * Tests the getLen method
     */
    public void testGetLen() {
        assertEquals(p1.getLen(), 1);

    }


    /**
     * Tests the setLen method
     */
    public void testSetLen() {
        assertEquals(p1.getLen(), 1);
        p1.setLen(2);
        assertEquals(p1.getLen(), 2);
    }

}
