package CoreMath;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:36:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class Derivative//
{
    public abstract double deriveFunction(double fx);     //returns a double...... the function//

    public double h = 1e-6;// DEFAULT degree of accuracy in the calculation//


    public double derivation(double InputFunc) {

        double value = 0.0;
        double X2 = 0.0;
        double X1 = 0.0;
        X2 = deriveFunction(InputFunc - h);
        X1 = deriveFunction(InputFunc + h);
        value = ((X1 - X2) / (2 * h));
        return value;
    }

    public double seconderiv(double InputFunc) {
        double value = 0.0;
        double X2 = 0.0;
        double X1 = 0.0;
        double basefunction = 0.0;
        X2 = deriveFunction(InputFunc - h);
        X1 = deriveFunction(InputFunc + h);
        basefunction = deriveFunction(InputFunc);
        value = ((X1 + X2 - (2 * basefunction)) / (h * h));
        return value;

    }


}

