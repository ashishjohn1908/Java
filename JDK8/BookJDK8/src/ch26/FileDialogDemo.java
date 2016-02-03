package ch26;

/* Demonstrate File Dialog box.

   This is an application, not an applet.
*/

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Create a subclass of Frame
class SampleFrame extends Frame {
    SampleFrame(String title) {
        super(title);

        // Remove the window when closed.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

    }
}

// Demonstrate FileDialog.
class FileDialogDemo {
    public static void main(String args[]) {
        // Create a frame that owns the dialog.
        Frame f = new SampleFrame("File Dialog Demo");
        f.setVisible(true);
        f.setSize(100, 100);

        FileDialog fd = new FileDialog(f, "File Dialog");

        fd.setVisible(true);
    }
}
