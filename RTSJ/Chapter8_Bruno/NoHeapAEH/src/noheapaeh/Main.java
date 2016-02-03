package noheapaeh;
import javax.realtime.*;
public class Main {
    public static void main(String[] args) throws Exception {
        NoHeapHelper nhrtHelper =
                new NoHeapHelper(MyLogic.class);
        nhrtHelper.start();
    }
}
