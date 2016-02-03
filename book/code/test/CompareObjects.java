import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 14-Jul-2009
 * Time: 14:30:27
 * To change this template use File | Settings | File Templates.
 */
public class CompareObjects {
    static Map<A1, String> map;

    public static void main(String[] args) {
        A1 a = new A1("Test");
        A1 b = new A1("Test");
        A1 c = a;

        if (a.hashCode() == b.hashCode()) {
            System.out.println("a.hashCode() == b.hashCode() - true");
        } else
            System.out.println("a.hashCode() == b.hashCode() - false");

        if (a.equals(b)) {
            System.out.println("a.equals(b) - true");
        } else {
            System.out.println("a.equals(b) - false");
        }

        if (a.hashCode() == c.hashCode()) {
            System.out.println("a.hashCode() == c.hashCode() - true");
        }

        if (c.equals(a)) {
            System.out.println("c.equals(a) - true");
        }

        map = new HashMap<A1, String>();
        map.put(new A1("test0"), "test0");
        map.put(new A1("test1"), "test2");
        map.put(new A1("test2"), "test3");
        map.put(new A1("test3"), "test4");
        map.put(new A1("test4"), "test5");
        map.put(new A1("test5"), "test0");


    }

}

final class A1 implements Cloneable {
    private final String name;
    private final long id;


    A1(String name) {
        this.name = name;
        this.id = 1;
    }

    public boolean equals(Object o) {
        return o instanceof A1 && this.name.equals(((A1) o).name);
    }

    public int hashCode() {
      // return this.name.hashCode() + super.hashCode();
        return 1;
    }

    protected A1 clone() {
        try {
            return (A1) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return this;
        }
    }
}