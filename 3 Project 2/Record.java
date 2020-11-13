import java.nio.ByteBuffer;

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
 * Class used to create and implement methods for a Record
 */
public class Record implements Comparable<Record> {
    private byte[] id;
    private byte[] key;
    private int flag;

    /**
     * Constructor for a record
     * 
     * @param i
     *            the byte array representing the id
     * @param k
     *            the byte array representing the key
     */
    public Record(byte[] i, byte[] k) {
        id = i;
        key = k;
    }


    /**
     * constructor for record with a flag
     * 
     * @param i
     *            the byte array representing the id
     * @param k
     *            the byte array representing the key
     * @param f
     *            the flag for the corresponding run
     */
    public Record(byte[] i, byte[] k, int f) {
        id = i;
        key = k;
        flag = f;
    }


    // Gets the int value for a record
    public int getInt() {
        return ByteBuffer.wrap(id).getInt();
    }


    // Gets the float value for a record
    public float getFloat() {
        return ByteBuffer.wrap(key).getFloat();
    }


    /**
     * Gets the flag for a record
     * 
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }


    /**
     * Sets the flag of a record
     * 
     * @param flag
     *            the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }


    /**
     * Gets the id of a record
     * 
     * @return the id
     */
    public byte[] getId() {
        return id;

    }


    /**
     * Sets the id of a record
     * 
     * @param id
     *            the id to set
     */
    public void setId(byte[] id) {
        this.id = id;
    }


    /**
     * Gets the key for a record
     * 
     * @return the key
     */
    public byte[] getKey() {
        return key;
    }


    /**
     * 
     * Sets the key for a record
     * 
     * @param key
     *            the key to set
     */
    public void setKey(byte[] key) {
        this.key = key;
    }


    /**
     * Compares two records
     * 
     * @param o
     *            record to compare
     * @return
     *         a value indicating the result of the comparison
     */
    @Override
    public int compareTo(Record o) {
        if (ByteBuffer.wrap(this.key).getFloat() > ByteBuffer.wrap(o.key)
            .getFloat()) {
            return 1;
        }
        else if (ByteBuffer.wrap(this.key).getFloat() < ByteBuffer.wrap(o.key)
            .getFloat()) {
            return -1;
        }
        else {
            return 0;
        }
    }

}
