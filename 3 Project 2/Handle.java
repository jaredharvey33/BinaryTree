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
 * * @author Ren Robinson (rarobin98), Jared Harvey (jharvey33)
 * 
 * @version 2020.11.23
 *
 * @param <K>
 *            Class to initialize handle
 */
public class Handle<K> {

    private K seqID;
    private K seq;
    
    
    public Handle() {
        seqID = null;
        seq = null;
    }

    /**
     * Creates a handle
     * 
     * @param id
     *            Sequence id
     * @param s
     *            Sequence
     * 
     */
    public Handle(K id, K s) {
        seqID = id;
        seq = s;
    }


    /**
     * Gets the Sequence ID
     * 
     * @return the sequence ID
     */
    public K getSeqID() {
        return seqID;
    }


    /**
     * Gets the sequence
     * 
     * @return the sequence
     */
    public K getSeq() {
        return seq;
    }
    

}
