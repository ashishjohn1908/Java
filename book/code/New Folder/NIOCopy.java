//listing 5
// Copy a file using NIO. 
import java.io.*; 
import java.nio.*; 
import java.nio.channels.*; 
 
public class NIOCopy { 
 
  public static void main(String args[]) { 
    FileInputStream fIn; 
    FileOutputStream fOut; 
    FileChannel fIChan, fOChan; 
    long fSize; 
    MappedByteBuffer mBuf; 
 
    try { 
      fIn = new FileInputStream(args[0]); 
      fOut = new FileOutputStream(args[1]); 
 
      // Get channels to the input and output files. 
      fIChan = fIn.getChannel(); 
      fOChan = fOut.getChannel(); 
 
      // Get the size of the file. 
      fSize = fIChan.size(); 
 
      // Map the input file to a buffer. 
      mBuf = fIChan.map(FileChannel.MapMode.READ_ONLY, 
                        0, fSize); 
 
      // Write the buffer to the output file. 
      fOChan.write(mBuf); // this copies the file 
 
      // Close the channels and files. 
      fIChan.close(); 
      fIn.close(); 
 
      fOChan.close(); 
      fOut.close(); 
    } catch (IOException exc) { 
      System.out.println(exc); 
      System.exit(1); 
    } catch (ArrayIndexOutOfBoundsException exc) {       
      System.out.println("Usage: Copy from to"); 
      System.exit(1); 
    } 
  } 
}
