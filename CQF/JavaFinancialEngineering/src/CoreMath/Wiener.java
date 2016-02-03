package CoreMath;

import static java.lang.Math.sqrt;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 23:57:20
 * To change this template use File | Settings | File Templates.
 */
public class Wiener {
    public Wiener() {
    }

    public double wienerProc(double t) {
        Random r = new Random();
        double epsilon = r.nextGaussian();
        return sqrt(t) * epsilon;
    }
}
