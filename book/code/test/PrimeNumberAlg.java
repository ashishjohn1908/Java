/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 29-Aug-2009
 * Time: 20:38:11
 * To change this template use File | Settings | File Templates.
 */
public class PrimeNumberAlg {

    static int[] A = new int[20];

    public static void main(String[] args) {

        for (int i = 1; i < A.length; i++) {
            if (isPrime(i))
                System.out.print(i + ", ");
        }
        System.out.println("\n" + "..............................");

    }

    static boolean isPrime(int p) {
        for (int i = 2; (i * i <= p); i++)
            if (p % i == 0) {
                return false;
            }

        return true;

    }

}
