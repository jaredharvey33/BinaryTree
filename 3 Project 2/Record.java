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
    private int id;
    private float key;

    public Record() {
        id = 0;
        key = 0;
    }


    public Record(ByteBuffer bb) {
        id = bb.getInt();
        key = bb.getFloat();
    }


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * @return the key
     */
    public float getKey() {
        return key;
    }


    /**
     * @param key
     *            the key to set
     */
    public void setKey(float key) {
        this.key = key;
    }


    /**
     * @param o
     * @return
     */
    @Override
    public int compareTo(Record o) {
        if (key > o.getKey()) {
            return -1;
        }
        else if (key < o.getKey()) {
            return 1;
        }
        else {
            return 0;
        }
    }

}
