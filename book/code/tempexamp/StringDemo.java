/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 17-Sep-2010
 * Time: 14:05:30
 * To change this template use File | Settings | File Templates.
 */
public class StringDemo {
    public static void main(String[] args) {
        String s = new String("plamen");
        CharSequence sequence = s.subSequence(0, s.length());
        System.out.println(sequence);
        boolean b = sequence.equals("plamen".subSequence(0, "plamen".length()));
        System.out.println(b);
    }
}
