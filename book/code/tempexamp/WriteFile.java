import java.applet.Applet;
import java.awt.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 03-Oct-2010
 * Time: 18:20:57
 * To change this template use File | Settings | File Templates.
 */
public class WriteFile extends Applet {
    String myFile = "C:\\Users\\plamen\\Downloads\\serials.txt";
    File f = new File(myFile);
    DataOutputStream dos;
    String osname;
    public void init() {

        osname = System.getProperty("os.name");
    }

    public void paint(Graphics g) {
        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(myFile), 128));
            dos.writeChars("Good job! Well done! You Moron! \n" + osname);
            dos.flush();
            g.drawString("Successfully wrote to the file named " + myFile + " -- go take a look at it!", 10, 10);
        } catch (SecurityException e) {
            g.drawString("writeFile: caught security exception: " + e, 10, 10);
        } catch (IOException ioe) {
            g.drawString("writeFile: caught i/o exception", 10, 10);
        }
    }
}


