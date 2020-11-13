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
 * Tests the RunCount class
 */
public class RunCountTest extends student.TestCase {

    private RunCount r;

    /**
     * sets up the tests classes.
     */
    public void setUp() {
        r = new RunCount(4, 0);

    }


    /*
     * Tests the getLength method
     */
    public void testGetLength() {
        assertEquals(r.getLength(), 4);
    }


    /*
     * Tests the setLength method
     */
    public void testSetLength() {
        r.setLength(3);
        assertEquals(r.getLength(), 3);
    }


    /*
     * Tests the getStart method
     */
    public void testGetStart() {
        assertEquals(r.getStart(), 0);
    }


    /*
     * Tests the setStart method
     */
    public void testSetStart() {
        r.setStart(2);
        assertEquals(r.getStart(), 2);
    }

}
