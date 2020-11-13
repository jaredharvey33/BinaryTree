import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
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
    public static final String recordFile = "Sampledata__32blocks.bin";
    public static final String outputFile = "test.bin";
    public static final String mergeFile = "merge.bin";

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // String recordFile = "Test2Block.bin";
        DataOutputStream f = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(outputFile)));

        byte[] outBuffer = new byte[BLOCK];

        RandomAccessFile raf = new RandomAccessFile(recordFile, "r");

        if (raf.length() <= B16) {
            replaceOnly(raf, outBuffer, f);
        }
        else {
            replaceMerge(raf, outBuffer, f);
        }
        f.close();

    }


    public static void replaceMerge(
        RandomAccessFile raf,
        byte[] outBuffer,
        DataOutputStream f)
        throws IOException {
        ArrayList<RunCount> runCounts = new ArrayList<>();
        int runCount = 0;
        int runTotal = 0;
        byte[] ib = new byte[BLOCK];
        Record[] heapArray = new Record[B16];
        Heap<Record> heap = new Heap<>(heapArray, 0, B16 / 8);
        int fullBlocks = (int)raf.length() / BLOCK;
        int offSet = 0;

        fillHeap(raf, heap, ib, 16);

        for (int i = 0; i < fullBlocks - 16; i++) { // looping through 1 block
                                                    // at a time

            int check = raf.read(ib, 0, BLOCK); // read in block
            if (check != -1) {

                for (int j = 0; j < BLOCK; j += 8) {// looping records in block
                    Record r;
                    r = heap.removeMin();
                    offSet = insertOut(r, outBuffer, offSet);
                    runCount++;

                    Record curr = getRecord(ib, j);

                    // Record topHeap = heap.check();

                    if (curr.compareTo(r) > 0) {
                        heap.insert(curr);
                    }
                    else {
                        heap.insert(curr);
                        heap.removeMin();

                        // check if all records are invisible
                        if (heap.heapsize() == 0) {
                            heap.sizeBack(B16 / 8);
                            heap.buildheap();
                            RunCount rc = new RunCount(runCount, runTotal);
                            runTotal += runCount;
                            runCounts.add(rc);
                            runCount = 0;
                        }

                    }

                } // inner for

            } // check
            else {
                for (int j = 0; j < raf.length() - fullBlocks * BLOCK; j += 8) {// looping
                                                                                // records
                                                                                // in
                                                                                // block
                    Record r;

                    r = heap.removeMin();
                    offSet = insertOut(r, outBuffer, offSet);
                    runCount++;

                    Record curr = getRecord(ib, j);

                    if (curr.compareTo(r) > 0) {
                        heap.insert(curr);
                    }
                    else {
                        heap.insert(curr);
                        heap.removeMin();

                        // check if all records are invisible
                        if (heap.heapsize() == 0) {
                            heap.sizeBack(B16 / 8);
                            heap.buildheap();
                            runTotal += runCount;
                            RunCount rc = new RunCount(runCount, runTotal);
                            runCounts.add(rc);
                            runCount = 0;
                        }
                    }

                } // check for

                // fileWrite(outBuffer, fileName);

            } // else

            fileWrite(outBuffer, f);
            offSet = 0;
            outBuffer = new byte[BLOCK];

        } // for

        RunCount rc = new RunCount(runCount, runTotal);
        runTotal += runCount;
        runCounts.add(rc);
        heap.sizeBack(B16 / 8);
        heap.buildheap();
        runCount = B16 / 8;
        RunCount rc1 = new RunCount(runCount, runTotal);
        runCounts.add(rc1);

        insertAll(heap, outBuffer, f);

        DataOutputStream fMerge = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(mergeFile)));
        RandomAccessFile rafRun = new RandomAccessFile(outputFile, "r");
        MergeSort ms = new MergeSort(heap, runCounts);
        ms.loadHeap(0, runCounts.size(), rafRun);
        ms.loadMergeFile(fMerge, rafRun);
    }


    /**
     * @param heap
     * @param outBuffer
     * @throws IOException
     */
    public static void insertAll(
        Heap<Record> heap,
        byte[] outBuffer,
        DataOutputStream f)
        throws IOException {

        int newLine = 0;
        for (int i = 0; i < 16; i++) {

            for (int j = 0; j < BLOCK; j += 8) {
                Record r = heap.removeMin();
                insertOut(r, outBuffer, j);
            }
            fileWrite(outBuffer, f);
            newLine++;
            if (newLine % 4 == 0) {
                System.out.println();
            }
            else if (heap.heapsize() != 0) {
                System.out.print(", ");
            }
            outBuffer = new byte[BLOCK];
        }
    }


    public static boolean checkEmpty(byte[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }


    public static int insertOut(Record r, byte[] out, int offSet) {
        byte[] id = r.getId();
        byte[] key = r.getKey();

        for (int j = 0; j < id.length; j++) {
            out[offSet + j] = id[j];
        }
        offSet = offSet + 4;

        for (int k = 0; k < key.length; k++) {
            out[offSet + k] = key[k];
        }
        offSet = offSet + 4;

        return offSet;

    }


    /**
     * @param raf
     * @throws IOException
     */
    public static void replaceOnly(
        RandomAccessFile raf,
        byte[] outBuffer,
        DataOutputStream f)
        throws IOException {
        byte[] ib = new byte[BLOCK];
        Record[] heapArray = new Record[B16];
        Heap<Record> heap = new Heap<>(heapArray, 0, B16);

        long fullBlocks = raf.length() / BLOCK;
        int offSet = 0;

        // put all of the data into the heap as we know it is not larger than 16
        // blocks
        fillHeap(raf, heap, ib, fullBlocks);

        int newLine = 0;
        while (heap.heapsize() != 0) {

            Record r = heap.removeMin();
            offSet = insertOut(r, outBuffer, offSet);

            if (offSet == BLOCK) {
                byte[] id = getRecord(outBuffer, 0).getId();
                byte[] key = getRecord(outBuffer, 0).getKey();
                System.out.print(ByteBuffer.wrap(id).getInt() + " " + ByteBuffer
                    .wrap(key).getFloat());
                fileWrite(outBuffer, f);
                offSet = 0;
                newLine++;
                if (newLine % 4 == 0) {
                    System.out.println();
                }
                else if (heap.heapsize() != 0) {
                    System.out.print(", ");
                }
            }

        } // while

    }


    public static void fileWrite(byte[] out, DataOutputStream f)
        throws IOException {
        f.write(out);
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
            raf.read(ib, 0, BLOCK);
            loadBytes(ib, heap, BLOCK);
        }

        // load remaining records
        // long remaining = raf.length() - fullB * BLOCK;
        // loadBytes(ib, heap, (int)remaining);
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
