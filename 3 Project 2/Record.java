import java.nio.ByteBuffer;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.10.28
 *
 */
public class Record implements Comparable<Record> {
    private byte[] id;
    private byte[] key;

    public Record(byte[] i, byte[] k) {
        id = i;
        key = k;
    }


    /**
     * @return the id
     */
    public int getId() {
        return ByteBuffer.wrap(id).getInt();

    }


    /**
     * @param id
     *            the id to set
     */
    public void setId(byte[] id) {
        this.id = id;
    }


    /**
     * @return the key
     */
    public float getKey() {
        return ByteBuffer.wrap(key).getFloat();
    }


    /**
     * @param key
     *            the key to set
     */
    public void setKey(byte[] key) {
        this.key = key;
    }


    /**
     * @param o
     * @return
     */
    @Override
    public int compareTo(Record o) {
        if (this.getKey() > o.getKey()) {
            return -1;
        }
        else if (this.getKey() < o.getKey()) {
            return 1;
        }
        else {
            return 0;
        }
    }

}
