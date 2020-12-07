import java.io.IOException;

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
public class Hashtable<K, V> {

    private MemoryManager<K> mm;
    private Entry[] table;
    private int n;

    public Hashtable(int size, MemoryManager<K> m) {
        table = initialize(size);
        mm = m;
        n = 0;
    }


    /**
     * @param size
     * @return
     */
    private Entry[] initialize(int size) {
        Entry[] t = new Entry[size];
        for (int i = 0; i < size; i++) {
            t[i] = new Entry();
        }
        return t;
    }


    public boolean insert(K id, K seq, int idx) throws IOException {

        int index = insertProbe(idx, id);

        // check for valid index
        if (index == -1) {
            System.out.println("SequenceID " + id.toString() + " exists");
            return false;
        }
        if (index == -2) {
            System.out.println("Bucket full. Sequence " + id.toString()
                + " could not be inserted");
            return false;
        }

        // get handle from mm
        Handle<Pair> h = mm.insert(id, seq);

        // create and insert entry
        Entry entry = new Entry(h);
        table[index] = entry;
        n++;
        return true;
    }


    private int insertProbe(int i, K key) throws IOException {
        int insert = -2; // records insertion index
        int bucket = i / 32 * 32; // first index for current bucket
        int idx = i;
        // initial case
        if (table[(idx % 32) + bucket].getFlag() != 1) {
            insert = (idx % 32) + bucket;
        }
        if (table[(idx % 32) + bucket].getFlag() == 1 && mm.getKey(table[(idx
            % 32) + bucket].getHandle()).equals(key)) {
            return -1;
        }

        idx++;

        // loop until every slot is checked
        while ((idx % 32) + bucket != i) {
            if (table[(idx % 32) + bucket].getFlag() != 1 && insert == -2) {
                insert = (idx % 32) + bucket;
            }
            if (table[(idx % 32) + bucket].getFlag() == 1 && mm.getKey(
                table[(idx % 32) + bucket].getHandle()).equals(key)) {
                return -1;
            }
            idx++;
        }

        return insert;
    }


    public boolean remove(K k) throws IOException {

        for (int i = 0; i < table.length; i++) {
            if (mm.getKey(table[i].getHandle()).equals(k)) {
                table[i].setFlag((short)-1);
                n--;
                return true;
            }
        }
        return false;
    }


    public void print() throws IOException {
        if (n == 0) {
            System.out.println("Sequence IDs:\nFree Block List: none");
            return;
        }
        System.out.println("Sequence IDs:");
        for (int i = 0; i < table.length; i++) {
            if (table[i].getFlag() == 1) {
                @SuppressWarnings("unchecked")
                K id = (K)mm.getKey(table[i].getHandle());
                System.out.println(id.toString() + ": hash slot [" + i + "]");
            }
        }
        mm.printList();

    }

}
