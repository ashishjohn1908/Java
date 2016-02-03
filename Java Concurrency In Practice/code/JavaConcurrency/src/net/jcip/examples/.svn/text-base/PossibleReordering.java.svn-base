package net.jcip.examples;

/**
 * PossibleReordering
 * <p/>
 * Insufficiently synchronized program that can have surprising results
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PossibleReordering {

    static int x = 0, y = 0;
    static volatile int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(new Runnable() {
                                                    public void run() {

                                                       //      synchronized (lock1){
                                                                a = 1;
                                                       //         if(b == 0)
                                                       //             try {
                                                       //                 Thread.sleep(10);
                                                       //             } catch (InterruptedException e) {
                                                       //                 e.printStackTrace();
                                                       //             }

                                                                x = b;
                                                      //       }
                                                    }
                                               }
                                );

        Thread other = new Thread(new Runnable() {
                                                    public void run() {

                                                           //  synchronized (lock2){
                                                                 b = 1;

                                                            //     if(a == 0)
                                                            //       try {
                                                            //         Thread.sleep(10);
                                                            //      } catch (InterruptedException e) {
                                                            //         e.printStackTrace();
                                                            //      }

                                                                 y = a;
                                                         //    }
                                                    }
                                                 }
                                 );
        one.start();
        other.start();
        one.join();
        other.join();

        System.out.println("( " + x + "," + y + ")");
    }
}
