import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 28-Jun-2010
 * Time: 16:07:51
 * To change this template use File | Settings | File Templates.
 */
public class XYLayout extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new XYLayout();
    }

    public XYLayout() {
        addWindowListener(new WindowAdapter() {
            void windowClosing() {
                System.exit(0);
            }
        });
        buildFrame();
        setLocation(250, 150);
        setVisible(true);
    }

    private void buildFrame() {
        JButton button;
        Dimension size;

        Container pane = getContentPane();
        pane.setLayout(null);

        Insets insets = pane.getInsets();

        button = new JButton("first");
        size = button.getPreferredSize();
        button.setBounds(25 + insets.left, 15 + insets.top, size.width, size.height);
        button.addActionListener(this);
        pane.add(button);

        button = new JButton("second");
        size = button.getPreferredSize();
        button.setBounds(55 + insets.left, 120 + insets.top, size.width, size.height);
        pane.add(button);

        button = new JButton("Exit");
        size = button.getPreferredSize();
        button.setBounds(150 + insets.left, 45 + insets.top, size.width + 50, size.height + 20);
        button.addActionListener(this);
        pane.add(button);

        int width = 300 + insets.left + insets.right;
        int height = 220 + insets.top + insets.bottom;
        setSize(width, height);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Exit")) {
            System.exit(0);
        } else if (s.equals("first")) {
            String[] possibleFlavor = {"Vanilla", "Chocolate", "Rocky Road"};
            String favorite = (String) JOptionPane.showInputDialog(this, "Chose your favorite",
                    "Ice Cream Flavor",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    possibleFlavor,
                    possibleFlavor[0]);


            if (favorite == null)
                System.out.println("No choice made");
            else
                System.out.println("Favorite: " + favorite);
        }
    }
}
