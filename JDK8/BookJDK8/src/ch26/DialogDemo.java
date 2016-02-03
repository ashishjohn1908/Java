package ch26;

// Demonstrate Dialog box.

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
/*
  <applet code="DialogDemo" width=250 height=250>
  </applet>
*/

// Create a subclass of Dialog.
class SampleDialog extends Dialog implements ActionListener {
    SampleDialog(Frame parent, String title) {
        super(parent, title, false);
        setLayout(new FlowLayout());
        setSize(300, 200);

        add(new Label("Press this button:"));
        Button b;
        add(b = new Button("Cancel"));
        b.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        dispose();
    }

    public void paint(Graphics g) {
        g.drawString("This is in the dialog box", 10, 70);
    }
}

// Create a subclass of Frame.
class MenuFrame1 extends Frame {
    String msg = "";
    CheckboxMenuItem debug, test;

    MenuFrame1(String title) {
        super(title);

        // create menu bar and add it to frame
        MenuBar mbar = new MenuBar();
        setMenuBar(mbar);

        // create the menu items
        Menu file = new Menu("File");
        MenuItem item1, item2, item3, item4;
        file.add(item1 = new MenuItem("New..."));
        file.add(item2 = new MenuItem("Open..."));
        file.add(item3 = new MenuItem("Close"));
        file.add(new MenuItem("-"));
        file.add(item4 = new MenuItem("Quit..."));
        mbar.add(file);

        Menu edit = new Menu("Edit");
        MenuItem item5, item6, item7;
        edit.add(item5 = new MenuItem("Cut"));
        edit.add(item6 = new MenuItem("Copy"));
        edit.add(item7 = new MenuItem("Paste"));
        edit.add(new MenuItem("-"));

        Menu sub = new Menu("Special", true);
        MenuItem item8, item9, item10;
        sub.add(item8 = new MenuItem("First"));
        sub.add(item9 = new MenuItem("Second"));
        sub.add(item10 = new MenuItem("Third"));
        edit.add(sub);

        // these are checkable menu items
        debug = new CheckboxMenuItem("Debug");
        edit.add(debug);
        test = new CheckboxMenuItem("Testing");
        edit.add(test);

        mbar.add(edit);

        // create an object to handle action and item events
        MyMenuHandler1 handler = new MyMenuHandler1(this);
        // register it to receive those events
        item1.addActionListener(handler);
        item2.addActionListener(handler);
        item3.addActionListener(handler);
        item4.addActionListener(handler);
        item5.addActionListener(handler);
        item6.addActionListener(handler);
        item7.addActionListener(handler);
        item8.addActionListener(handler);
        item9.addActionListener(handler);
        item10.addActionListener(handler);
        debug.addItemListener(handler);
        test.addItemListener(handler);

        // create an object to handle window events
        MyWindowAdapter1 adapter = new MyWindowAdapter1(this);
        // register it to receive those events
        addWindowListener(adapter);
    }

    public void paint(Graphics g) {
        g.drawString(msg, 10, 200);

        if (debug.getState())
            g.drawString("Debug is on.", 10, 220);
        else
            g.drawString("Debug is off.", 10, 220);

        if (test.getState())
            g.drawString("Testing is on.", 10, 240);
        else
            g.drawString("Testing is off.", 10, 240);
    }
}

class MyWindowAdapter1 extends WindowAdapter {
    MenuFrame1 menuFrame;

    public MyWindowAdapter1(MenuFrame1 menuFrame) {
        this.menuFrame = menuFrame;
    }

    public void windowClosing(WindowEvent we) {
        menuFrame.dispose();
    }
}

class MyMenuHandler1 implements ActionListener, ItemListener {
    MenuFrame1 menuFrame;

    public MyMenuHandler1(MenuFrame1 menuFrame) {
        this.menuFrame = menuFrame;
    }

    // Handle action events
    public void actionPerformed(ActionEvent ae) {
        String msg = "You selected ";
        String arg = ae.getActionCommand();
        // Activate a dialog box when New is selected.
        if (arg.equals("New...")) {
            msg += "New.";
            SampleDialog d = new
                    SampleDialog(menuFrame, "New Dialog Box");
            d.setVisible(true);
        }
        // Try defining other dialog boxes for these options.
        else if (arg.equals("Open..."))
            msg += "Open.";
        else if (arg.equals("Close"))
            msg += "Close.";
        else if (arg.equals("Quit..."))
            msg += "Quit.";
        else if (arg.equals("Edit"))
            msg += "Edit.";
        else if (arg.equals("Cut"))
            msg += "Cut.";
        else if (arg.equals("Copy"))
            msg += "Copy.";
        else if (arg.equals("Paste"))
            msg += "Paste.";
        else if (arg.equals("First"))
            msg += "First.";
        else if (arg.equals("Second"))
            msg += "Second.";
        else if (arg.equals("Third"))
            msg += "Third.";
        else if (arg.equals("Debug"))
            msg += "Debug.";
        else if (arg.equals("Testing"))
            msg += "Testing.";
        menuFrame.msg = msg;
        menuFrame.repaint();
    }

    public void itemStateChanged(ItemEvent ie) {
        menuFrame.repaint();
    }
}

// Create frame window.
public class DialogDemo extends Applet {
    Frame f;

    public void init() {
        f = new MenuFrame1("Menu Demo");
        int width = Integer.parseInt(getParameter("width"));
        int height = Integer.parseInt(getParameter("height"));

        setSize(width, height);

        f.setSize(width, height);
        f.setVisible(true);
    }

    public void start() {
        f.setVisible(true);
    }

    public void stop() {
        f.setVisible(false);
    }
}
