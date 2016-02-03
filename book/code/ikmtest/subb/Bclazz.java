package subb;

import supper.Aclazz;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 29-Sep-2010
 * Time: 16:12:09
 * To change this template use File | Settings | File Templates.
 */

class B {

    String name = "B";

    void redValue() {
        System.out.println("LB_1");
    }
}

public class Bclazz extends B {

    String name = "Bclazz";

    public void redValue() {
        System.out.println("LB_2");
    }


    public static void main(String[] args) {
        B lb = new Bclazz();
        lb.redValue();
        System.out.println(lb.name);

    }
}
