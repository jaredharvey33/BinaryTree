import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.11.09
 *
 */
public class Externalsorting {

    public static final int BLOCK = 1024 * 8;
    public static final int B16 = 1024 * 8 * 16;

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            RandomAccessFile raf = new RandomAccessFile("Sampledata16.bin",
                "r");
            if (raf.length() <= B16) {
                replaceOnly(raf);
            }
            byte[] b = new byte[BLOCK];
            raf.read(b, 0, BLOCK);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * @param raf
     * @throws IOException
     */
    public static void replaceOnly(RandomAccessFile raf) throws IOException {
        byte[] ib = new byte[BLOCK];
        Record[] heapArray = new Record[B16];
        Heap<Record> heap = new Heap<>(heapArray, 0, B16);

        long fullBlocks = raf.length() / BLOCK;

        // put all of the data into the heap as we know it is not larger than 16
        // blocks
        fillHeap(raf, heap, ib, fullBlocks);
        
        //output the data
        
    }


    public static void fillHeap(
        RandomAccessFile raf,
        Heap<Record> heap,
        byte[] ib,
        long fullB)
        throws IOException {

        // loop for number of full blocks, loading one block of data into the
        // heap each time
        for (int i = 0; i < fullB; i++) {
            raf.read(ib, i * BLOCK, BLOCK);
            loadBytes(ib, heap, BLOCK);
        }

        // load remaining records
        long remaining = raf.length() - fullB * BLOCK;
        loadBytes(ib, heap, (int)remaining);

    }


    /**
     * @param ib
     * @param heap
     */
    public static void loadBytes(byte[] ib, Heap<Record> heap, int bytes) {
        for (int i = 0; i < bytes; i += 8) {
            heap.insert(getRecord(ib, i));
        }

    }


    public static Record getRecord(byte[] b, int idx) {
        byte[] id = new byte[4];
        byte[] key = new byte[4];
        id = Arrays.copyOfRange(b, idx, idx + 4);
        key = Arrays.copyOfRange(b, idx + 4, idx + 8);
        Record r = new Record(id, key);
        return r;

    }

}
