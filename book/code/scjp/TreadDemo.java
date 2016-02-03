import java.util.concurrent.Executor;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 06/02/11
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class TreadDemo {

    static class RunnableThread implements Runnable{

        public void run() {

            for(int i = 0; i < 10;i++){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                System.out.println();
            }

            System.out.println("Running");


        }
    }

    static class RunnableThread1 implements Runnable{

        public void run() {

            for(int i = 0; i < 100;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                System.out.println();
            }

            System.out.println("Running");


        }
    }

    public static void main(String[] args) {
         Executor executor = new DirectExecutor();
         executor.execute(new RunnableThread());
         Executor executor1 = new ThreadPerTaskExecutor();
         executor1.execute(new RunnableThread1());
    }

    static class DirectExecutor implements Executor {
        public void execute(Runnable r) {
           // r.run();
             new Thread(r).start();
        }
    }


    static class ThreadPerTaskExecutor implements Executor {
        public void execute(Runnable r) {
            new Thread(r).start();
        }
    }
}
