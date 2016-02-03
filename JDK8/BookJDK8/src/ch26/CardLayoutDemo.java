package ch26;

// Demonstrate CardLayout.

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/*
  <applet code="CardLayoutDemo" width=300 height=100>
  </applet>
*/

public class CardLayoutDemo extends Applet
        implements ActionListener, MouseListener {

    Checkbox windowsXP, windows7, windows8, android, solaris, mac;
    Panel osCards;
    CardLayout cardLO;
    Button Win, Other;

    public void init() {
        Win = new Button("Windows");
        Other = new Button("Other");
        add(Win);
        add(Other);

        cardLO = new CardLayout();
        osCards = new Panel();
        osCards.setLayout(cardLO); // set panel layout to card layout

        windowsXP = new Checkbox("Windows XP", null, true);
        windows7 = new Checkbox("Windows 7", null, false);
        windows8 = new Checkbox("Windows 8", null, false);
        android = new Checkbox("Android");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("Mac OS");

        // add Windows check boxes to a panel
        Panel winPan = new Panel();
        winPan.add(windowsXP);
        winPan.add(windows7);
        winPan.add(windows8);

        // Add other OS check boxes to a panel
        Panel otherPan = new Panel();
        otherPan.add(android);
        otherPan.add(solaris);
        otherPan.add(mac);

        // add panels to card deck panel
        osCards.add(winPan, "Windows");
        osCards.add(otherPan, "Other");

        // add cards to main applet panel
        add(osCards);

        // register to receive action events
        Win.addActionListener(this);
        Other.addActionListener(this);

        // register mouse events
        addMouseListener(this);
    }

    // Cycle through panels.
    public void mousePressed(MouseEvent me) {
        cardLO.next(osCards);
    }

    // Provide empty implementations for the other MouseListener methods.
    public void mouseClicked(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Win) {

            cardLO.show(osCards, "Windows");
        } else {
            cardLO.show(osCards, "Other");
        }
    }
}

