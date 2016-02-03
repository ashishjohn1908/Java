import java.util.TimerTask;
import java.util.Timer;


/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 07-Sep-2009
 * Time: 15:39:54
 * To change this template use File | Settings | File Templates.
 */
public class CallMessageTime extends TimerTask {
    public void run() {
        //   while(true){
        try {
            callMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //   }
    }

    private void callMessage() {
        System.out.println("Message Called");
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new CallMessageTime(), (long) 10000, (long) 10000);
    }
}
