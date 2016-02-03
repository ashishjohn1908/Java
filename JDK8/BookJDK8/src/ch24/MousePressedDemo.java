package ch24;

// This applet does NOT use an inner class.

import java.applet.Applet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
  <applet code="MousePressedDemo" width=200 height=100>
  </applet>

*/

public class MousePressedDemo extends Applet {
    public void init() {
        addMouseListener(new MyMouseAdapter1(this));
    }
}

class MyMouseAdapter1 extends MouseAdapter {
    MousePressedDemo mousePressedDemo;

    public MyMouseAdapter1(MousePressedDemo mousePressedDemo) {
        this.mousePressedDemo = mousePressedDemo;
    }

    public void mousePressed(MouseEvent me) {
        mousePressedDemo.showStatus("Mouse Pressed.");
    }
}
