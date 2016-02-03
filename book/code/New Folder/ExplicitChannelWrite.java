//listing 3
// Write to a file using the new I/O. 
import java.io.*; 
import java.nio.*; 
import java.nio.channels.*; 
 
public class ExplicitChannelWrite { 
  public static void main(String args[]) { 
    FileOutputStream fOut; 
    FileChannel fChan; 
    ByteBuffer mBuf; 
 
    try { 
      fOut = new FileOutputStream("test.txt"); 
 
      // Get a channel to the output file. 
      fChan = fOut.getChannel(); 
 
      // Create a buffer. 
      mBuf = ByteBuffer.allocateDirect(26); 
 
      // Write some bytes to the buffer. 
      for(int i=0; i<26; i++)  
        mBuf.put((byte)('A' + i)); 
 
      // Rewind the buffer so that it can written. 
      mBuf.rewind(); 
 
      // Write the buffer to the output file. 
      fChan.write(mBuf); 
 
      // close channel and file. 
      fChan.close(); 
      fOut.close(); 
    } catch (IOException exc) { 
      System.out.println(exc); 
      System.exit(1); 
    } 
  } 
}