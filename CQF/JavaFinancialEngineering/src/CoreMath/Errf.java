package CoreMath;

import static java.lang.Math.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:37:11
 * To change this template use File | Settings | File Templates.
 */
public class Errf extends PolyEval// After Abramovitz  et al   //
{
    private static double p = 0.3275911;
    private double[] polycofs = {0.0, 0.254829592, -0.284496736, 1.421413741, -1.453152027, 1.061405429};//to use Horner's rule add constant//
    private double[] answers = new double[2];
    private double derivative;
    private double compliment;

    private void setDeriv(double der) {
        derivative = der;
    }

    public double getDerivative() {
        return derivative;
    }

    private void setComp(double errorf, double sign) {          // adjusted for -ve x values//
        compliment = sign < 0 ? (2 - (1 - errorf)) : 1 - errorf;
    }

    public double getErfc() {
          return compliment;
    }

    public void polycoeffs(double[] input) {
          setcoefficients(input);
    }

    private void setcoefficients(double[] input) {
        polycofs = input;
    }

    public double erf(double x) {                //THIS USE'S  HORNERS RULE//
        double sign = x;
        x = abs(x);
        double norm = exp(-pow(x, 2));
        double norm2 = norm * 2 / sqrt(PI);//derivative of the error function as norm2//
        setDeriv(norm2);
        if (x == 0) {
            setComp(0, 0);
            return 0;
        } else {
            double t = 1.0 / (1.0 + p * x);
            polycoeffs(polycofs);
            answers = evalDerv(t);
            double v0 = (1.0 - answers[0] * norm);
            setComp(v0, sign);// compliment is set prior to adjusting for sign//
            return sign < 0 ? -v0 : v0;
        }
    }

}


