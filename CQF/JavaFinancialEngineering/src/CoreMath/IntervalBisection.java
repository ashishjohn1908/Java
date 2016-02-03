package CoreMath;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:47:38
 * To change this template use File | Settings | File Templates.
 */
public abstract class IntervalBisection {


    //computeFunction is implemented to evaluate successive root estimates//
    public abstract double computeFunction(double rootvalue);

    protected double precisionvalue;
    protected int iterations;
    protected double lowerBound;
    protected double upperBound;

    //default constructor//
    protected IntervalBisection() {


        iterations = 20;
        precisionvalue = 1e-6;
    }

    //Constructor with user defined repetitions and precision//
    protected IntervalBisection(int iterations, double precisionvalue) {
        this.iterations = iterations;
        this.precisionvalue = precisionvalue;
    }

    public int getiterations() {
        return iterations;
    }

    public void setiterations(int iterations) {
        this.iterations = iterations;
    }

    public double getprecisionvalue() {
        return precisionvalue;
    }

    public void setprecisionvalue(double precisionvalue) {
        this.precisionvalue = precisionvalue;
    }

    public double evaluateRoot(double lower, double higher) {
        System.out.println("iterations== " + iterations);
        double fa;
        double fb;
        double fc = 0.0;
        double midvalue = 0;
        int i = 0;
        double precvalue = 0;
        // int counterval=0;
        fa = computeFunction(lower);
        System.out.println(" fa  =" + fa + "  lower input value==" + lower);
        fb = computeFunction(higher);
        System.out.println(" fb  =" + fb + " higher input value ==" + higher);

        //Check to see if we have the root within the range bounds//
        if (fa * fb > 0) {
            System.out.println("Wrong values:");
            midvalue = 0;//Terminate program put in error trapping//
        } else
            do {
                precvalue = midvalue;//preceding value for testing relative precision//
                i++;
                double prechigh = higher;
                double preclow = lower;
                midvalue = lower + 0.5 * (higher - lower);
                fc = computeFunction(midvalue);


                if (fa * fc < 0) {
                    higher = midvalue;

                } else if (fa * fc > 0) {
                    lower = midvalue;

                }
                //counterval++;
                // System.out.println("COUNTER=="+counterval);
            }


            while ((Math.abs(midvalue - precvalue) > precisionvalue & i < iterations));//loops until desired number of iterations or precision is reached//

        return midvalue;
    }

}
