import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

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

    public static final int BLOCK = 1024;

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            RandomAccessFile raf = new RandomAccessFile("Sampledata16.bin",
                "r");
            byte[] b = new byte[BLOCK];
            raf.read(b, 0, BLOCK);
            ByteBuffer buffer = ByteBuffer.wrap(b, 0, 8);
            System.out.println(buffer.getFloat());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
