package ch9;

/**
 * Created by plamen on 24/08/2014.
 */
class TestIface {
    public static void main(String args[]) {
        Callback c = new Client();
        c.callback(42);
    }
}
