package ch27;

/*
 * <applet code="SimpleImageLoad" width=400 height=345>
 *  <param name="img" value="Lilies.jpg">
 * </applet>
 */

import java.applet.Applet;
import java.awt.*;

public class SimpleImageLoad extends Applet {
    Image img;

    public void init() {
        img = getImage(getDocumentBase(), getParameter("img"));
    }

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
}

