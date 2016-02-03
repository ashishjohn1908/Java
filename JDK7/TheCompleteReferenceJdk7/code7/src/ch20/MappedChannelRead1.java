package ch20;
// Use a mapped file to read a file. Pre-JDK 7 version.

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class MappedChannelRead1 {
  public static void main(String args[]) {
    FileInputStream fIn = null;
    FileChannel fChan = null;
    long fSize;
    MappedByteBuffer mBuf;

    try {
      // First, open a file for input.
      fIn = new FileInputStream("test.txt");

      // Next, obtain a channel to that file.
      fChan = fIn.getChannel();

      // Get the size of the file.
      fSize = fChan.size();

      // Now, map the file into a buffer.
      mBuf = fChan.map(FileChannel.MapMode.READ_ONLY, 0, fSize);

      // Read and display bytes from buffer.
      for(int i=0; i < fSize; i++)
        System.out.print((char)mBuf.get());

    } catch (IOException e) {
      System.out.println("I/O Error " + e);
    } finally {
      try {
        if(fChan != null) fChan.close(); // close channel
      } catch(IOException e) {
        System.out.println("Error Closing Channel.");
      }
      try {
        if(fIn != null) fIn.close(); // close file
      } catch(IOException e) {
        System.out.println("Error Closing File.");
      }
    }
  }
}