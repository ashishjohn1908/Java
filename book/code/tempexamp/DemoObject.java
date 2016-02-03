/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 03/02/11
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
public class DemoObject {
    private static Double d1 = new Double(5.0);

    public static void main(String[] args) {

        Double d = new Double(2.0);

        change(d);

        System.out.println(d);

    }

    private static void change(Double d) {
        // d1 = new Double(5.0);
        d = d1;
    }
    /*
     private static void change(double d) {
         d = 3.0;
     }
    */
}