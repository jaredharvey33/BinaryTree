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
public class HandleTest extends student.TestCase {

    
    Handle<Pair> h1;
    private Pair p1;
    private Pair p2;

    
    /**
     * sets up the tests classes.
     */
    public void setUp() {
        p1 = new Pair(1, 1);
        p2 = new Pair(2, 2);
        h1 = new Handle<Pair>(p1,p2);
        
    }// end setUp


    /**
     * Tests the getSeqID method
     */
    public void testgetSeqID() {
        assertEquals(h1.getSeqID(),p1);
    }

    
}
