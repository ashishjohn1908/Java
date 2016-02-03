package ch10;

/**
 * Created by plamen on 24/08/2014.
 */
class Exc1 {
    static void subroutine() {
        int d = 0;
        int a = 10 / d;
    }

    public static void main(String args[]) {
        Exc1.subroutine();
    }
}
