// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.10.12
 *
 */
public class KeyVector<K1 extends Comparable<K1>, K2 extends Comparable<K2>, K3 extends Comparable<K3>>
    implements Comparable<KeyVector<?, ?, ?>> {

    private K1 key1;
    private K2 key2;
    private K3 key3;

    public KeyVector() {
        key1 = null;
        key2 = null;
        key3 = null;
    }


    public KeyVector(K1 k1, K2 k2) {
        key1 = k1;
        key2 = k2;
        key3 = null;
    }


    public KeyVector(K1 k1, K2 k2, K3 k3) {
        key1 = k1;
        key2 = k2;
        key3 = k3;
    }


    /**
     * @return the key1
     */
    public K1 getKey1() {
        return key1;
    }


    /**
     * @param key1
     *            the key1 to set
     */
    public void setKey1(K1 key1) {
        this.key1 = key1;
    }


    /**
     * @return the key2
     */
    public K2 getKey2() {
        return key2;
    }


    /**
     * @param key2
     *            the key2 to set
     */
    public void setKey2(K2 key2) {
        this.key2 = key2;
    }


    /**
     * @return the key3
     */
    public K3 getKey3() {
        return key3;
    }


    /**
     * @param key3
     *            the key3 to set
     */
    public void setKey3(K3 key3) {
        this.key3 = key3;
    }


    /**
     * @param o
     * @return
     */
    public int compareTo(KeyVector<?, ?, ?> kv) {
        if (this.key1.compareTo((K1)kv.getKey1()) < 0) {
            return -1;
        }
        else if (key1.compareTo((K1)kv.getKey1()) > 0) {
            return 1;
        }

        else {
            if (this.key2.compareTo((K2)kv.getKey2()) < 0) {
                return -1;
            }
            else if (key2.compareTo((K2)kv.getKey2()) > 0) {
                return 1;
            }

            else {
                if (key3 == null) {
                    return 0;
                }
                else if (this.key3.compareTo((K3)kv.getKey3()) < 0) {
                    return -1;
                }
                else if (key3.compareTo((K3)kv.getKey3()) > 0) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }

    }

}
