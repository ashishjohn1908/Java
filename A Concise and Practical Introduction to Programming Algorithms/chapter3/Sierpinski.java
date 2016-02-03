import javax.swing.*;
import java.awt.*;

public class Sierpinski extends JFrame {
    public static final int WINDOW_SIZE = 512;
    public static final int THRESHOLD = 10; // stopping criterion for recursion
    public static int P1_x, P1_y, P2_x, P2_y, P3_x, P3_y;

    public Sierpinski() {
        super("Sierpinski");
        setSize(WINDOW_SIZE, WINDOW_SIZE);

        // A simple triangle
        P1_x = (int) getSize().getWidth() / 2;

        P1_y = 40;
        P2_x = 20;
        P2_y = (int) getSize().getHeight() - 20;
        P3_x = (int) getSize().getWidth() - 20;
        P3_y = (int) getSize().getHeight() - 20;

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Compute the midpoint
    public Point getMiddle(Point p1, Point p2) {
        return new Point((int) (p1.getX() + p2.getX()) / 2, (int) (p1.getY() + p2.getY()) / 2);
    }

    public void paint(Graphics g) {
        super.paint(g);
        sierpinski_draw(new Point(P1_x, P1_y), new Point(P2_x, P2_y), new Point(P3_x, P3_y));
    }

    public void sierpinski_draw(Point p1, Point p2, Point p3) {
        //termination condition
        if (p1.distance(p2) < THRESHOLD && p1.distance(p3) < THRESHOLD &&
                p2.distance(p3) < THRESHOLD) return; // stop recursion


        //draw the current triangle
        Graphics g = getGraphics();
        g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
        g.drawLine((int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());
        g.drawLine((int) p3.getX(), (int) p3.getY(), (int) p1.getX(), (int) p1.getY());

        //recursively draw the 3 smaller corner triangles
        Point m12 = getMiddle(p1, p2);
        Point m23 = getMiddle(p2, p3);
        Point m31 = getMiddle(p3, p1);

        // Recursive calls
        sierpinski_draw(p1, m12, m31);
        sierpinski_draw(p2, m23, m12);
        sierpinski_draw(p3, m31, m23);
    }

    public static void main(String[] args) {
        new Sierpinski();
    }
}
