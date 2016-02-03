package CoreMath;

import static java.lang.Math.abs;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:30:20
 * To change this template use File | Settings | File Templates.
 */
public abstract class ContFract {
    public double prec = 1E-30;
    private double nume;
    private double denom;
    private double interim;
    private double lentzval;
    private double oldans;
    private int n = 1;
    double[] vars = new double[2];

    abstract void computeFract(int n);

    public void setInitial(double numerator, double denominator) {

        nume = numerator;
        denom = denominator;

    }

    public void setInt(int n) {
        this.n = n;
    }

    private int getInt() {
        return n;
    }


    public void setFrac(double initial) {
        //initial value of Fn //
        lentzval = initial;

    }

    public double floorvalue(double x) {
        return abs(x) < Csmallnumber.getSmallnumber() ? Csmallnumber.getSmallnumber() : x;
    }

    public double getFrac() {
        return lentzval;
    }

    public void evalFract()// lentzs method..................//
    {
        int i = getInt();


        while (abs(oldans - 1.0) > prec)// terminating criteria      //
        {

            computeFract(++i);//set up the a, b, x and initial values for //
            denom = floorvalue((vars[1] + vars[0] * denom));// array contains numerator and denominator  //
            denom = (1.0 / denom);
            nume = floorvalue((vars[1] + (vars[0] / nume)));
            oldans = nume * denom;
            lentzval *= (nume * denom);// Cn * Dn   //


        }
        setFrac(lentzval);

    }


}

