import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Scanner;

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
 * @version 2020.11.20
 *
 */
public class DNAdbase {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // String commandFile = args[0];
        String commandFile = "P4SampleInput.txt";

        // int hashSize = Integer.parseInt(args[2]);
        int hashSize = 64;

        // String memFile = args[3];
        String memFile = "file.bin";

        if (hashSize % 32 != 0) {
            System.out.println(
                "Error: hashtable size must be a multiple of 32");
            return;
        }

        RandomAccessFile raf = new RandomAccessFile(memFile, "rw");
        MemoryManager<String> mm = new MemoryManager<>(raf);
        Hashtable<String, Entry> hash = new Hashtable<>(hashSize, mm);
        Scanner scan = null;

        try {
            scan = new Scanner(new File(commandFile));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNext()) {
            String line = scan.nextLine().trim().replaceAll("\\s+", " ");
            String[] splitLine = line.split(" ");
            String command = splitLine[0];

            if (command.equalsIgnoreCase("insert")) {
                String id = splitLine[1];
                int length = Integer.parseInt(splitLine[2]);
                line = scan.nextLine().trim().replaceAll("\\s+", " ");
                String seq = line;

                // Check for incorrect given length
                if (seq.length() != length) {
                    System.out.println("Warning: Actual sequence length (" + seq
                        .length() + ") does not match given length (" + length
                        + ")");
                }
                hash.insert(id, seq, (int)sfold(id, hashSize));

            }
            else if (command.equalsIgnoreCase("print")) {
                hash.print();
            }
            else if (command.equalsIgnoreCase("remove")) {

            }
            else if (command.equalsIgnoreCase("search")) {

            }

        }
        raf.close();

    }


    public static long sfold(String s, int M) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char c[] = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        sum = (sum * sum) >> 8;
        return (Math.abs(sum) % M);
    }
}
