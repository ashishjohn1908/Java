package CoreMath;

import static java.lang.Math.abs;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:51:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class NewtonRaphson extends Derivative {
    int counter = 0;

    public abstract double newtonroot(double rootvalue);  //the requesting function implements the calculation fx//

    public double precisionvalue = 0.0;

    public int iterate = 0;

    public void accuracy(double precision, int iterations)//method gets the desired accuracy//
    {
        super.h = precision;//sets the superclass derivative to the desired precision//
        this.precisionvalue = precision;
        this.iterate = iterations;
    }

    public double newtraph(double lowerbound) { //System.out.println("Accuravcy levels=="+precisionvalue+"ITERATIONS=="+iterate);
        double fx = 0.0;
        double Fx = 0.0;
        double x = 0.0;
        fx = floorvalue(newtonroot(lowerbound));

        Fx = floorvalue(derivation(lowerbound));

        x = floorvalue((lowerbound - (fx / Fx)));


        while ((Math.abs(x - lowerbound) > precisionvalue & counter < iterate)) {

            lowerbound = x;
            fx = newtonroot(lowerbound);
            Fx = derivation(lowerbound);
            x = floorvalue((lowerbound - (fx / Fx)));
            counter++;
        }
        //System.out.println("The Solution is:....................."+x);
        return x;
    }

    public double deriveFunction(double inputa) {
        double x1 = 0.0;
        x1 = newtonroot(inputa);
        return x1;

    }

    public double floorvalue(double x) {
        return abs(x) < Csmallnumber.getSmallnumber() ? Csmallnumber.getSmallnumber() : x;
    }
}



