import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.11.11
 *
 */
public class MergeSort {
    private Heap<Record> heap;
    private byte[] inBuffer;
    private byte[] outBuffer;
    private ArrayList<RunCount> runs;
    private int[] bytesRemain;
    private static final int BLOCK = Externalsorting.BLOCK;
    private static final int B16 = Externalsorting.B16;

    public MergeSort(Heap<Record> h, ArrayList<RunCount> run)
        throws FileNotFoundException {
        heap = h;
        runs = run;
        inBuffer = new byte[BLOCK];
        outBuffer = new byte[BLOCK];
        bytesRemain = new int[runs.size()];

    }


    /**
     * @return the heap
     */
    public Heap<Record> getHeap() {
        return heap;
    }


    /**
     * @param heap
     *            the heap to set
     */
    public void setHeap(Heap<Record> heap) {
        this.heap = heap;
    }


    public void loadHeap(int start, int end, RandomAccessFile raf)
        throws IOException {

        int seek = 0;
        for (int i = start; i < end; i++) {
            seek = runs.get(i).getStart() * 8;
            raf.seek(seek);
            raf.read(inBuffer, 0, BLOCK);
            int size = Math.min(runs.get(i).getLength() * 8, BLOCK);

            // load the heap with a block or less of data
            for (int j = 0; j < size; j += 8) {
                Record r = Externalsorting.getRecord(inBuffer, j);
                r.setFlag(i);
                r.getInt();
                r.getFloat();
// if (j == 0) {
//
// System.out.println(r.getInt() + " "
// + r.getFloat() + " here");
// }
                heap.insert(r);
            }
            bytesRemain[i] += size;
            runs.get(i).setStart(size);
        }

    }


    public void loadMergeFile(DataOutputStream f, RandomAccessFile raf)
        throws IOException {
        int offSet = 0;
        for (int i = 0; i < raf.length(); i += 8) {
            Record r = heap.removeMin();
            offSet = Externalsorting.insertOut(r, outBuffer, offSet);
            bytesRemain[r.getFlag()] -= 8;
            if (bytesRemain[r.getFlag()] == 0 && hasMoreData(r.getFlag())) {
                loadHeap(r.getFlag(), r.getFlag() + 1, raf);
            }
            if (offSet == BLOCK) {
                // check last merge
                byte[] id = Externalsorting.getRecord(outBuffer, 0).getId();
                byte[] key = Externalsorting.getRecord(outBuffer, 0).getKey();
                System.out.println(ByteBuffer.wrap(id).getInt() + " "
                    + ByteBuffer.wrap(key).getFloat());
                Externalsorting.fileWrite(outBuffer, f);
                offSet = 0;
                outBuffer = new byte[BLOCK];
            }
        }

    }


    /**
     * @param flag
     * @return
     */
    public boolean hasMoreData(int flag) {
        if (runs.get(flag).getStart() == sumTo(flag + 1)) {
            return false;
        }
        return true;

    }


    /**
     * @param end
     * @return
     */
    public int sumTo(int end) {
        int sum = 0;
        for (int i = 0; i < end; i++) {
            sum += runs.get(i).getLength();
        }
        return sum;
    }

}
