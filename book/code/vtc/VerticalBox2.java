import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 28-Jun-2010
 * Time: 14:12:10
 * To change this template use File | Settings | File Templates.
 */
public class VerticalBox2 extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new VerticalBox2();
    }

    public VerticalBox2() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        buildFrame();
        setLocation(250, 250);
        setVisible(true);
    }

    private void buildFrame() {
        JButton button;

        Container pane = getContentPane();
        BoxLayout boxLayout = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(boxLayout);

        pane.add(Box.createRigidArea(new Dimension(100, 10)));
        button = new JButton("First");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        pane.add(button);
        pane.add(Box.createRigidArea(new Dimension(100, 10)));
        button = new JButton("Second");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        pane.add(button);
        pane.add(Box.createRigidArea(new Dimension(100, 10)));
        button = new JButton("Third");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        pane.add(button);
        pane.add(Box.createRigidArea(new Dimension(100, 10)));
        button = new JButton("Fourth");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        pane.add(button);
        pane.add(Box.createRigidArea(new Dimension(100, 10)));
        button = new JButton("Exit");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        pane.add(button);

        pack();
    }

    private void setSize(Component c) {
        c.setPreferredSize(new Dimension(80, 25));
        c.setMaximumSize(new Dimension(80, 25));
        c.setMinimumSize(new Dimension(80, 25));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        String s = e.getActionCommand();
        if (s.equals("Exit")) {
            System.exit(0);
        } else if (s.equals("First")) {
            System.out.println("First");
        } else if (s.equals("Second")) {
            System.out.println("Second");
        } else if (s.equals("Third")) {
            System.out.println("Third");
        } else if (s.equals("Fourth")) {
            System.out.println("Fourth");
        }
    }
}
