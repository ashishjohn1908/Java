/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 17-Jun-2010
 * Time: 13:35:10
 * To change this template use File | Settings | File Templates.
 */
class C {
    public static void main(String[] args) {
        Super sup = new Sub();
        System.out.println("sub");
    }
}

class Super {
    Super(int i) {
        System.out.println("super " + i);
    }

}

class Sub extends Super {

    Sub() {
        super(5);
    }

}