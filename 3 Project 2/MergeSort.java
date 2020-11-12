import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
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
    private RandomAccessFile raf;
    private Heap<Record> heap;
    private byte[] inBuffer;
    private byte[] outBuffer;
    private ArrayList<RunCount> runs;
    private String outFile;
    private int[] bytesRemain;
    private static final int BLOCK = Externalsorting.BLOCK;
    private static final int B16 = Externalsorting.B16;

    public MergeSort(
        RandomAccessFile r,
        Heap<Record> h,
        String fn,
        ArrayList<RunCount> run) {
        raf = r;
        heap = h;
        runs = run;
        inBuffer = new byte[BLOCK];
        outBuffer = new byte[BLOCK];
        outFile = fn;
        bytesRemain = new int[runs.size()];

    }


    public void loadHeap(int start, int end) throws IOException {

        int seek = 0;
        for (int i = start; i < end; i++) {
            seek = runs.get(i).getStart() - runs.get(i).getLength()
                + bytesRemain[i];
            raf.seek(seek);
            raf.read(inBuffer, 0, BLOCK);
            int size = Math.min(runs.get(i).getLength(), BLOCK);
            bytesRemain[i] = size;
            // load the heap with a block or less of data
            for (int j = 0; j < size; j += 8) {
                Record r = Externalsorting.getRecord(inBuffer, j);
                r.setFlag(i);
                heap.insert(r);
            }
        }

    }


    public void loadMergeFile() throws IOException {
        int offSet = 0;
        for (int i = 0; i < raf.length(); i += 8) {
            Record r = heap.removeMin();
            offSet = Externalsorting.insertOut(r, outBuffer, i);
            if (bytesRemain[r.getFlag()] - 8 == 0) {
                loadHeap(r.getFlag(), r.getFlag() + 1);
            }
            if (offSet == BLOCK) {
                Externalsorting.fileWrite(outBuffer, outFile);
                offSet = 0;
                outBuffer = new byte[BLOCK];
            }
        }

    }

}
