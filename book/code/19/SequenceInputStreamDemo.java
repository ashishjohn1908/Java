// Demonstrate sequenced input.

import java.io.*;
import java.util.*;

class InputStreamEnumerator implements Enumeration<FileInputStream> {
    private Enumeration<String> files;

    public InputStreamEnumerator(ArrayList<String> files) {
        this.files = Collections.enumeration(files);
    }

    public boolean hasMoreElements() {
        return files.hasMoreElements();
    }

    public FileInputStream nextElement() {
        try {
            return new FileInputStream(files.nextElement());
        } catch (IOException e) {
            return null;
        }
    }
}

class SequenceInputStreamDemo {
    public static void main(String args[]) throws IOException {

        int c;
        ArrayList<String> files = new ArrayList<String>();

        files.add("C:\\PDF\\Java\\book\\code\\19\\file2.txt");
        files.add("C:\\PDF\\Java\\book\\code\\19\\file3.txt");
        InputStreamEnumerator e = new InputStreamEnumerator(files);
        InputStream input = new SequenceInputStream(e);

        while ((c = input.read()) != -1) {
            System.out.print((char) c);
        }

        input.close();
    }
}
