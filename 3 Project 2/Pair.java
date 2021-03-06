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
public class Pair {

    private int loc;
    private int len;

    /**
     * Constructor for a pair
     * 
     * @param location
     *            offset in file
     * @param length
     *            length in file
     */
    public Pair(int location, int length) {
        loc = location;
        len = length;
    }


    /**
     * toString for a pair
     */
    public String toString() {
        return "Starting Byte Location: " + loc + ", Size " + len + " bytes";
    }


    /**
     * Get the location for a pair
     * 
     * @return the loc
     */
    public int getLoc() {
        return loc;
    }


    /**
     * Sets the location for a pair
     * 
     * @param loc
     *            the loc to set
     */
    public void setLoc(int loc) {
        this.loc = loc;
    }


    /**
     * Gets the length for a pair
     * 
     * @return the len
     */
    public int getLen() {
        return len;
    }


    /**
     * Sets the length of a pair
     * 
     * @param len
     *            the len to set
     */
    public void setLen(int len) {
        this.len = len;
    }

}
