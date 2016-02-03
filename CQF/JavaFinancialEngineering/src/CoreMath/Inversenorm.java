package CoreMath;

import BaseStats.Probnorm;

import static java.lang.Math.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 23:06:17
 * To change this template use File | Settings | File Templates.
 */
public class Inversenorm extends NewtonRaphson{

    public Inversenorm() {
        accuracy(1e-9, 20);//optimum values for xup to 5
    }

    Probnorm p = new Probnorm();
    private double target = 0.0;

    public double InverseNormal(double uvalue)//Probability
//between 0 and 1.0
    {
        double xval = 0.0;
        target = uvalue;
        if (target == 0.5)
            return 0.0;
        if (uvalue < 0.5) {
            uvalue = (1.0 - uvalue);
            xval = -sqrt(abs(-1.6 * log(1.0004 - pow((1.0 - 2.0 * uvalue), 2))));
            return newtraph(xval);
        } else {
            xval = sqrt(abs(-1.6 * log(1.0004 - pow((1.0 - 2.0 * uvalue), 2))));
        }
        return newtraph(xval);
    }

    public double newtonroot(double rootinput) {
        return (target - p.ncDisfnc(rootinput));
    }
}
