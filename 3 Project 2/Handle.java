// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.11.23
 *
 */
public class Handle<K> {

    private K seqID;
    private K seq;

    public Handle(K id, K s) {
        seqID = id;
        seq = s;
    }


    /**
     * @return the seqID
     */
    public K getSeqID() {
        return seqID;
    }


    /**
     * @return the seq
     */
    public K getSeq() {
        return seq;
    }

}
