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
public class Entry {

    private short flag;
    private Handle<Pair> handle;

    /**
     * Empty constructor
     */
    public Entry() {
        flag = 0;
    }


    /**
     * Constructor for a Entry
     * 
     * @param h
     *            a handle
     */
    public Entry(Handle<Pair> h) {
        handle = h;
        flag = 1;
    }


    /**
     * Gets the flag of an entry
     * 
     * @return the f
     *         the flag value
     */
    public short getFlag() {
        return flag;
    }


    /**
     * Sets the flag for an entry
     * 
     * @param f
     *            the flag to set
     */
    public void setFlag(short f) {
        this.flag = f;
    }


    /**
     * Gets the handle for an entry
     * 
     * @return the handle to get
     */
    public Handle<Pair> getHandle() {
        return handle;
    }


    /**
     * Sets the handle for an entry
     * 
     * @param h
     *            the handle to set
     */
    public void setHandle(Handle<Pair> h) {
        this.handle = h;
    }

}
