package ch26;

// Demonstrate Labels

import java.applet.Applet;
import java.awt.*;
/*
<applet code="LabelDemo" width=300 height=200>
</applet>
*/

public class LabelDemo extends Applet {
    public void init() {
        Label one = new Label("One");
        Label two = new Label("Two");
        Label three = new Label("Three");

        // add labels to applet window
        add(one);
        add(two);
        add(three);
    }
}
