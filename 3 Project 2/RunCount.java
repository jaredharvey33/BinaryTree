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
 * Creates a counter for the runs
 */
public class RunCount {
    private int length;
    private int start;

    /**
     * Creates a run count
     * 
     * @param l
     *            Length of the run
     * @param s
     *            start location of the run
     */
    public RunCount(int l, int s) {
        length = l;
        start = s;
    }


    /**
     * Gets the length of a run
     * 
     * @return the length
     */
    public int getLength() {
        return length;
    }


    /**
     * Sets the length of a run
     * 
     * @param length
     *            the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }


    /**
     * Gets the start of the run
     * 
     * @return the start
     */
    public int getStart() {
        return start;
    }


    /**
     * Sets the start of the run
     * 
     * @param start
     *            the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

}
