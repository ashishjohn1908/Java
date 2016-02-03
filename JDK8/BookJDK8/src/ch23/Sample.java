package ch23;

/* A simple applet that sets the foreground and
   background colors and outputs a string. */

import java.applet.Applet;
import java.awt.*;
/*
<applet code="Sample" width=300 height=50>
</applet>
*/

public class Sample extends Applet {
    String msg;

    // set the foreground and background colors.
    public void init() {
        setBackground(Color.cyan);
        setForeground(Color.red);
        msg = "Inside init( ) --";
    }

    // Initialize the string to be displayed.
    public void start() {
        msg += " Inside start( ) --";
    }

    // Display msg in applet window.
    public void paint(Graphics g) {
        msg += " Inside paint( ).";
        g.drawString(msg, 10, 30);
    }
}

