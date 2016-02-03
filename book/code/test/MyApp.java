import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 07-Jul-2010
 * Time: 13:32:18
 * To change this template use File | Settings | File Templates.
 */
class MyApp implements java.io.Serializable {
    //  BigObjectThatShouldNotBeSerializedWithAButton bigOne;
    Button aButton = new Button();

    static class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Hello There");
        }
    }

    MyApp() {
        aButton.addActionListener(new MyActionListener());
    }
}

