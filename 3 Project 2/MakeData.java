
import java.io.*;
import java.util.*;
import java.math.*;

public class MakeData{

static final int NumRecs = 1024; // Each record holds 8 bytes. Each block has 8192 bytes
    
/** Initialize the random variable */
static private Random value = new Random(); // Hold the Random class object

    
static int randInt() {
    return value.nextInt(Integer.MAX_VALUE );
}

static float randFloat() {
    return value.nextFloat()*Float.MAX_VALUE;
}
    
public static void main(String args[]) throws IOException {
    int val;
    float val2;
  int filesize = 17; // Size of file in blocks
  DataOutputStream file = new DataOutputStream(
      new BufferedOutputStream(new FileOutputStream("Test2Block.bin")));

  for (int i=0; i<filesize; i++)
      for (int j=0; j<NumRecs; j++) {
         val = (int)(randInt());
         file.writeInt(val);
         val2 = (float)(randFloat());
         file.writeFloat(val2);
      }
  
  file.flush();
  file.close();
}

}
