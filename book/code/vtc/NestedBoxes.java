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
 * Time: 14:50:51
 * To change this template use File | Settings | File Templates.
 */
public class NestedBoxes extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new NestedBoxes();
    }

    public NestedBoxes() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            ;
        });
        buildFrame();
        setLocation(250, 250);
        setVisible(true);
    }

    private void buildFrame() {
        Container pane = getContentPane();
        BoxLayout boxLayout = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(boxLayout);

        pane.add(Box.createRigidArea(new Dimension(10, 10)));

        JLabel label = new JLabel("A line of the text for the top of the window...");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(label);

        pane.add(Box.createRigidArea(new Dimension(10, 10)));

        JPanel middle = buildMiddle();
        pane.add(middle);

        pane.add(Box.createRigidArea(new Dimension(10, 10)));

        JPanel bottom = buildBottom();
        bottom.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(bottom);

        pane.add(Box.createRigidArea(new Dimension(10, 10)));

        pack();
    }

    private JPanel buildMiddle() {
        JPanel middle = new JPanel();
        middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));

        middle.add(Box.createRigidArea(new Dimension(10, 10)));
        JTextArea t1 = new JTextArea(12, 20);
        middle.add(t1);

        middle.add(Box.createRigidArea(new Dimension(10, 10)));
        JTextArea t2 = new JTextArea(12, 20);
        middle.add(t2);

        middle.add(Box.createRigidArea(new Dimension(10, 10)));

        return middle;
    }

    private JPanel buildBottom() {

        JButton button;
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));

        bottom.add(Box.createHorizontalGlue());
        button = new JButton("First");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        bottom.add(button);

        bottom.add(Box.createRigidArea(new Dimension(10, 10)));
        button = new JButton("Second");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        bottom.add(button);
        bottom.add(Box.createRigidArea(new Dimension(10, 10)));
        button = new JButton("Third");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        bottom.add(button);
        bottom.add(Box.createRigidArea(new Dimension(10, 10)));
        button = new JButton("Fourth");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        bottom.add(button);
        bottom.add(Box.createRigidArea(new Dimension(10, 10)));
        button = new JButton("Exit");
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        setSize(button);
        bottom.add(button);

        bottom.add(Box.createRigidArea(new Dimension(10, 10)));

        return bottom;
    }

    private void setSize(Component c) {
        c.setPreferredSize(new Dimension(80, 25));
        c.setMaximumSize(new Dimension(80, 25));
        c.setMinimumSize(new Dimension(80, 25));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
