import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 16-Jun-2010
 * Time: 15:28:38
 * To change this template use File | Settings | File Templates.
 */
public class JavaTestExmp implements I1 {
    static int total = 10;

    JavaTestExmp() {

    }

    public void call() {
        int total = 5;
        System.out.println(this.total);
    }

    public static void main(String[] args) {

        JavaTestExmp exmp = new JavaTestExmp();
        exmp.call();
        int j = 0;
        int a[] = {2, 4};
        do for (int i : a)
            System.out.print(i + " ");
        while (j++ < 1);


    }


}


interface I1 {
    abstract void call();
}



