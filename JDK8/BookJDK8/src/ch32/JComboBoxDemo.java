package ch32;

// Demonstrate JComboBox.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
  <applet code="JComboBoxDemo" width=300 height=200>
  </applet>
*/

public class JComboBoxDemo extends JApplet {
    JLabel jlab;
    ImageIcon hourglass, analog, digital, stopwatch;
    JComboBox<String> jcb;

    String timepieces[] = {"Hourglass", "Analog", "Digital", "Stopwatch"};

    public void init() {
        try {
            SwingUtilities.invokeAndWait(
                    new Runnable() {
                        public void run() {
                            makeGUI();
                        }
                    }
            );
        } catch (Exception exc) {
            System.out.println("Can't create because of " + exc);
        }
    }

    private void makeGUI() {

        // Change to flow layout.
        setLayout(new FlowLayout());

        // Instantiate a combo box and add it to the content pane.
        jcb = new JComboBox<String>(timepieces);
        add(jcb);

        // Handle selections.
        jcb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String s = (String) jcb.getSelectedItem();
                jlab.setIcon(new ImageIcon(this.getClass().getResource(s + ".png")));
            }
        });


        // Create a label and add it to the content pane.
        jlab = new JLabel(new ImageIcon(this.getClass().getResource("Hourglass.png")));
        add(jlab);
    }

}
