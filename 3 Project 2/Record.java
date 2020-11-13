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
    private int flag;

    public Record(byte[] i, byte[] k) {
        id = i;
        key = k;
    }


    public Record(byte[] i, byte[] k, int f) {
        id = i;
        key = k;
        flag = f;
    }


    public int getInt() {
        return ByteBuffer.wrap(id).getInt();
    }


    public float getFloat() {
        return ByteBuffer.wrap(key).getFloat();
    }


    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }


    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }


    /**
     * @return the id
     */
    public byte[] getId() {
        return id;

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
    public byte[] getKey() {
        return key;
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
