// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.11.30
 *
 */
public class Entry {

    private short flag;
    private Handle<Pair> handle;

    public Entry() {
        flag = 0;
    }


    public Entry(Handle<Pair> h) {
        handle = h;
        flag = 1;
    }


    /**
     * @return the f
     */
    public short getFlag() {
        return flag;
    }


    /**
     * @param f
     *            the f to set
     */
    public void setFlag(short f) {
        this.flag = f;
    }


    /**
     * @return the h
     */
    public Handle<Pair> getHandle() {
        return handle;
    }


    /**
     * @param h
     *            the h to set
     */
    public void setHandle(Handle<Pair> h) {
        this.handle = h;
    }

}
