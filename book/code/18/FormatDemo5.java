// Demonstrate the space format specifiers. 

import java.util.*;

class FormatDemo5 {
    public static void main(String args[]) {
        Formatter fmt = new Formatter();

        fmt.format("% d", -100);
        System.out.println(fmt);

        fmt = new Formatter();
        fmt.format("% d", 100);
        System.out.println(fmt);

        fmt = new Formatter();
        fmt.format("% d", -200);
        System.out.print(fmt);

        fmt = new Formatter();
        fmt.format("% (d", -200);
        System.out.println(" or " + fmt);

        fmt = new Formatter();
        fmt.format("% d", 200);
        System.out.println(fmt);
    }
}