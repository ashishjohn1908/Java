/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 28-Jun-2010
 * Time: 13:15:30
 * To change this template use File | Settings | File Templates.
 */
public class ExampleVCT implements Runnable {

    private Integer i = 2;

    @Override
    public void run() {
        synchronized (i) {
            System.out.println("Done");
        }
    }


    public static void main(String[] args) {
        ExampleVCT vct = new ExampleVCT();
        new Thread(vct).start();
        new Thread(vct).start();

    }
}
