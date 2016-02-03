package ch20;
// Write to a mapped file. Pre JDK 7 version.
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class MappedChannelWrite1 {
  public static void main(String args[]) {
    RandomAccessFile fOut = null;
    FileChannel fChan = null;
    ByteBuffer mBuf;

    try {
      fOut = new RandomAccessFile("test.txt", "rw");

      // Next, obtain a channel to that file.
      fChan = fOut.getChannel();

      // Then, map the file into a buffer.
      mBuf = fChan.map(FileChannel.MapMode.READ_WRITE, 0, 26);

      // Write some bytes to the buffer.
      for(int i=0; i<26; i++)
        mBuf.put((byte)('A' + i));

    } catch (IOException e) {
      System.out.println("I/O Error " + e);
    } finally {
      try {
        if(fChan != null) fChan.close(); // close channel
      } catch(IOException e) {
        System.out.println("Error Closing Channel.");
      }
      try {
        if(fOut != null) fOut.close(); // close file
      } catch(IOException e) {
        System.out.println("Error Closing File.");
      }
    }
  }
}