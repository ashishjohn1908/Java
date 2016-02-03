// Demonstrate ByteArrayOutputStream.

import java.io.*;

class ByteArrayOutputStreamDemo {
    public static void main(String args[]) throws IOException {
        ByteArrayOutputStream f = new ByteArrayOutputStream();
        String s = "This should end up in the array";
        byte buf[] = s.getBytes();

        f.write(buf);
        System.out.println("Buffer as a string");
        System.out.println(f.toString());
        System.out.println("Into array");
        byte b[] = f.toByteArray();
        for (int i = 0; i < b.length; i++) {
            System.out.print((char) b[i]);
        }
        System.out.println("\nTo an OutputStream()");
        OutputStream f2 = new FileOutputStream("test.txt");

        f.writeTo(f2);
        f2.close();
        System.out.println("Doing a reset");
        f.reset();
        for (int i = 0; i < 3; i++)
            f.write('X');
        System.out.println(f.toString());
    }
}
