package plamen;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 15-Dec-2009
 * Time: 21:52:53
 * To change this template use File | Settings | File Templates.
 */
public class MathModulus {

    public static void main(String[] args) {
        int a = 10;
        int b = 4;
        int i = 1;
        do {

            if ((a % i) == 2)
                System.out.println(i);
            i++;
        } while (i < 11);
    }
}
