package ch26;

// Demonstrate BorderLayout.

import java.applet.Applet;
import java.awt.*;
/*
<applet code="BorderLayoutDemo" width=400 height=200>
</applet>
*/

public class BorderLayoutDemo extends Applet {
    public void init() {
        setLayout(new BorderLayout());

        add(new Button("This is across the top."),
                BorderLayout.NORTH);
        add(new Label("The footer message might go here."),
                BorderLayout.SOUTH);
        add(new Button("Right"), BorderLayout.EAST);
        add(new Button("Left"), BorderLayout.WEST);

        String msg = "The reasonable man adapts " +
                "himself to the world;\n" +
                "the unreasonable one persists in " +
                "trying to adapt the world to himself.\n" +
                "Therefore all progress depends " +
                "on the unreasonable man.\n\n" +
                "        - George Bernard Shaw\n\n";

        add(new TextArea(msg), BorderLayout.CENTER);
    }
}