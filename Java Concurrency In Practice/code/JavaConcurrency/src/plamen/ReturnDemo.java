package plamen;

/**
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 20-Nov-2010
 * Time: 00:13:22
 * To change this template use File | Settings | File Templates.
 */
public class ReturnDemo {

    public static void main(String[] args) {
        for(int i =1; i <=5; i++)
            returnMethod();
        Integer integer = returnMethod();
        System.out.println("done : " + integer);
    }

    private static int returnMethod() {
            return 1;
    }
}
