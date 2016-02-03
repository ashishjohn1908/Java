import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 26/02/11
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
public class ArrayDemo {
    public static void main(String[] args) {

        char[] copyFrom = { 'd', 'e', 'c', 'a', 'f', 'f', 'e', 'i', 'n', 'a', 't', 'e', 'd' };
               char[] copyTo = new char[7];

              // System.arraycopy(copyFrom, 2, copyTo, 0, 7);
               copyTo = Arrays.copyOfRange(copyFrom,2,9);
               System.out.println(new String(copyTo));

        int rs = 5^6;
        System.out.println(rs);



    }
}
