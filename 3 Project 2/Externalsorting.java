import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String recordFile = "Test2Block.bin";
        String outputFile = "test.bin";

        byte[] inBuffer = new byte[BLOCK];
        byte[] outBuffer = new byte[BLOCK];

        RandomAccessFile raf = new RandomAccessFile(recordFile, "r");
        
        if (raf.length() <= B16) {
            replaceOnly(raf, outBuffer, outputFile);
        }
        else {
            replaceMerge(raf, outBuffer, outputFile);
        }
        byte[] b = new byte[BLOCK];
        raf.read(b, 0, BLOCK);

    }


    public static void replaceMerge(
        RandomAccessFile raf,
        byte[] outBuffer,
        String fileName)
        throws IOException {
        byte[] ib = new byte[BLOCK];
        Record[] heapArray = new Record[B16];
        Heap<Record> heap = new Heap<>(heapArray, 0, B16);
        int fullBlocks = (int)raf.length() / BLOCK;
     

        fillHeap(raf, heap, ib, 16);
         
        
        for (int i = 0; i < fullBlocks - 16; i++) { // looping through 1 block
            outBuffer = new byte[BLOCK];                                       // at time
            int offSet = 0;
           
           int check = raf.read(ib, 0, BLOCK-1); // read in block
           if(check != -1) {
               
            for (int j = 0; j < BLOCK; j += 8) {// looping records in block
                Record r;
               
                if (checkEmpty(outBuffer)) {
                   
                    r = heap.removeMin();
                    offSet = insertOut(r, outBuffer, offSet);

                    Record curr = getRecord(ib, i);
                    heap.insert(curr);

                }
                else {
                    r = getRecord(outBuffer, offSet - 8);
                }

                Record topHeap = heap.check();

                if (topHeap.compareTo(r) > 0) {
                    offSet = insertOut(topHeap, outBuffer, offSet);
                }
                else {
                    heap.removeMin();
                }
                if (offSet == 1024) {
                    fileWrite(outBuffer, fileName);
                    offSet = 0;
                }

            } // inner for
            
           }//check 
           else {
           for (int j = 0; j < raf.length() - fullBlocks * BLOCK; j += 8) {// looping records in block
               Record r;
               if (checkEmpty(outBuffer)) {

                   r = heap.removeMin();
                   offSet = insertOut(r, outBuffer, offSet);

                   Record curr = getRecord(ib, i);
                   heap.insert(curr);

               }
               else {
                   r = getRecord(outBuffer, offSet - 8);
               }

               Record topHeap = heap.check();

               if (topHeap.compareTo(r) > 0) {
                   offSet = insertOut(topHeap, outBuffer, offSet);
               }
               else {
                   heap.removeMin();
               }
            

           } // check for
           
           fileWrite(outBuffer, fileName);
           
           }//else
           

        } // for

        raf.read(ib,0,BLOCK);
        
        if (heap.heapsize() == 0) {
            heap.sizeBack(1024);
            heap.buildheap();
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
        System.out.println(ByteBuffer.wrap(id).getInt() + " " + ByteBuffer.wrap(
            key).getFloat());

        return offSet;

    }


    /**
     * @param raf
     * @throws IOException
     */
    public static void replaceOnly(
        RandomAccessFile raf,
        byte[] outBuffer,
        String fileName)
        throws IOException {
        byte[] ib = new byte[BLOCK];
        Record[] heapArray = new Record[B16];
        Heap<Record> heap = new Heap<>(heapArray, 0, B16);

        long fullBlocks = raf.length() / BLOCK;
        int offSet = 0;

        // put all of the data into the heap as we know it is not larger than 16
        // blocks
        fillHeap(raf, heap, ib, fullBlocks);

        for (int i = 0; i < heap.heapsize(); i++) {

            Record r = heap.removeMin();
            offSet = insertOut(r, outBuffer, offSet);

            if (offSet == 1024) {
                fileWrite(outBuffer, fileName);
                offSet = 0;
            }

        } // for
          // output the data

    }


    public static void fileWrite(byte[] out, String fName) throws IOException {
        DataOutputStream f = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(fName)));
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
