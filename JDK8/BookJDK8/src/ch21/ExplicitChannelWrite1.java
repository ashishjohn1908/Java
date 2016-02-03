package ch21;

// Write to a file using NIO. Pre-JDK 7 Version.

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ExplicitChannelWrite1 {
    public static void main(String args[]) {
        FileOutputStream fOut = null;
        FileChannel fChan = null;
        ByteBuffer mBuf;

        try {
            // First, open the output file.
            fOut = new FileOutputStream("test.txt");

            // Next, get a channel to the output file.
            fChan = fOut.getChannel();

            // Create a buffer.
            mBuf = ByteBuffer.allocate(26);

            // Write some bytes to the buffer.
            for (int i = 0; i < 26; i++)
                mBuf.put((byte) ('A' + i));

            // Rewind the buffer so that it can be written.
            mBuf.rewind();

            // Write the buffer to the output file.
            fChan.write(mBuf);
        } catch (IOException e) {
            System.out.println("I/O Error " + e);
        } finally {
            try {
                if (fChan != null) fChan.close(); // close channel
            } catch (IOException e) {
                System.out.println("Error Closing Channel.");
            }
            try {
                if (fOut != null) fOut.close(); // close file
            } catch (IOException e) {
                System.out.println("Error Closing File.");
            }
        }
    }
}
