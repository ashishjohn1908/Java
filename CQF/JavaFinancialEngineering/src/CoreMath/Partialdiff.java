package CoreMath;

import static java.lang.Math.pow;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:28:29
 * To change this template use File | Settings | File Templates.
 */
public class Partialdiff {// takes the form: y^2x/yx^2..fractional input

    /**
     * Creates a new instance of Partialdiff
     */
    public Partialdiff() {
    }

    private double diffone;
    private double difftwo;

    private void setOne(double fdiff) {
        diffone = fdiff;
    }

    private void setTwo(double sdiff) {
        difftwo = sdiff;
    }

    public double getFdiff() {
        return diffone;
    }

    public double getSdiff() {
        return difftwo;
    }

    public void diffValues(double[] coefficients, double x)//equation terms from 1 to n, x the the value of the variable (numerator)
    {
        double dp2 = 0.0;
        double dp = 0.0;
        int cnt = 0;
        int n = -1;
        double[] firstdiff = new double[coefficients.length];
        for (double d : coefficients) {
            firstdiff[cnt] = (d * n * pow(x, n - 1));
            dp2 += ((d * n * (n - 1)) * pow(x, n - 2));
            dp += firstdiff[cnt];
            System.out.println("Answer  " + firstdiff[cnt] + "TOTAL== dp2=" + dp2 + "+dp==" + dp + " pow x,n==" + (pow(x, n)) + " n-1 value ==" + (pow(x, n - 1)));
            cnt++;
            n--;
        }
        setOne(dp);
        setTwo(dp2);
    }

    //REMOVE THIS AFTER TESTING.....................................................//

    /*
   public static void main(String[] args)
{ double[] arrayvalsp={0.07,0.07,0.07,1.07};
        // double[] arrayvals={0.0,0.12,0.12,1.12};
         double x=1.07;

        diffValues(arrayvalsp,x);


    }
    */

}


