package FinApps;

import CoreMath.Interpolate;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:21:30
 * To change this template use File | Settings | File Templates.
 */
public class Intermstructure extends Interms {
    /**
     * Instantiates the class Interpolate as object It provides interpolation of yield
     * data
     */
    Interpolate It = new Interpolate();
    Spots s = new Spots();
    /**
     * varaible flagging for setting of current rates (default == 0)
     */
    private int current_flag = 0;
    /**
     * The array containing yield data First column contains time variables second
     * column contains observed yield data
     */
    private double[][] currentdata;// Current data is ASSUMED TO BE spot rates taken from DMO yields or user defined yields

    /**
     * Creates a new instance of Intermstructure
     */
    public Intermstructure() {
    }


    public double lagraninterp(double[][] xyterms, double xvalue) {
        return It.lagrange(xyterms, xvalue);
    }

    public double[][] lagraninterp(double[][] xyterms, double[] xvalue) {
        int size;
        size = (xyterms.length + xvalue.length);
        double[][] tempterms = new double[size][2];
        int j = 0;
        int i = 1;
        int h = 1;
        tempterms[0][0] = xyterms[0][0];
        tempterms[0][1] = xyterms[0][1];
        tempterms[size - 1][0] = xyterms[xyterms.length - 1][0];
        tempterms[size - 1][1] = xyterms[xyterms.length - 1][1];
        for (int k = 1; k < size - 1; k++) {

            if ((tempterms[k - 1][0] < xvalue[j]) & (xvalue[j] < xyterms[h][0])) {
                tempterms[k][0] = xvalue[j];
                tempterms[k][1] = It.lagrange(xyterms, xvalue[j]);

                j++;

            } else {
                tempterms[k][0] = xyterms[h][0];
                tempterms[k][1] = xyterms[h][1];

                h++;

            }

        }
        return tempterms;
    }

    /**
     * computes the discount rate for period one at the rate
     *
     * @param time_1   The time period variable for which the discount is computed
     * @param interate the interest rate for computing the discount
     * @return returns the time1 discount period
     */
    public double DiscpOne(double interate, double time_1) {
        return disFromyld(interate, time_1);

    }

    /**
     * spot interest rate calculation for time 1
     *
     * @param time_1   The time variable for computing the spot rate
     * @param interate the input interest rate (discount factor) for time 1
     * @return The spot rate for time 1
     */
    public double SpotpOne(double interate, double time_1) {

        return yldFromdisc(interate, time_1);
    }

    /**
     * computes the forward discount rate from the time 1 and time 2 discount rates
     *
     * @param interate_1 The discount factor for time one
     * @param interate_2 The discount factor for time two
     * @param time_1     The time period for computing the first discount factor
     * @param time_2     The time period for computing the second discount factor
     * @return The forward discount rate for times 1 and 2
     */
    public double Forwdisc(double interate_1, double interate_2, double time_1, double time_2) {
        return forateFromdisc(interate_1, interate_2, time_1, time_2);
    }

    /**
     * computes the forward rate of interest as yield for two time periods
     *
     * @param interate_1 The period one spot rate
     * @param interate_2 The period two sp[ot rate
     * @param time_1     The time period for computing first spot rate
     * @param time_2     The time period for computing the second spot rate
     * @return The forward spot rate for times 1 and 2
     */
    public double Forwyld(double interate_1, double interate_2, double time_1, double time_2) {
        return forateFromyld(interate_1, interate_2, time_1, time_2);
    }

    /**
     * method that sets the current rate of interest from raw data
     *
     * @param yielddata input data array contains the raw time/yield data for bonds
     */
    public void setCurrentRateData(double[][] yielddata) {
        currentdata = new double[yielddata.length][2];
        currentdata = yielddata;
        current_flag = 1;
        Intermspots();
    }

    /**
     * method assumes coupon data is input as yield data with the first entry being a zero coupon value with yield == spot rate
     * spotdata takes price/coupon data assumed in linear order of periods 1..n
     */
    public void setCurrentSpotFc(double[][] spotdata) {
        int i = 0;
        double[] spotyields;
        currentdata = new double[spotdata.length][2];
        spotyields = s.spotFcoupon(spotdata);
        for (double d : spotyields) {
            currentdata[i][1] = d;
            currentdata[i][0] = (double) i + 1;
            i++;
        }

        current_flag = 1;
        Intermspots();
    }

    /**
     * method to retrieve the currently set discount rate for time 1
     *
     * @param timepoint_1 The time used to compute the discount factor from the yield
     * @return returns current discount rate for time 1
     */
    public double getCurrentDiscOne(double timepoint_1) {

        return Errorcheck(timepoint_1) == 1 ? disFromyld(It.lagrange(currentdata, timepoint_1), timepoint_1) : 0.0;

    }

    /**
     * method to retrieve the current spot rate for time 1 (current yield)
     *
     * @param timepoint_1 The time period used to compute the spot rate from yield data
     * @return the current spot rate for time 1
     */
    public double getCurrentSpotOne(double timepoint_1) {

        return Errorcheck(timepoint_1) == 1 ? It.lagrange(currentdata, timepoint_1) : 0.0;
    }


    /**
     * method to compute the forward rate for spots from yields
     *
     * @param timepoint_1 The time period used to compute the first spot rate
     * @param timepoint_2 The time period used to compute the second spot rate
     * @return forward spot rate for times 1 and 2
     */
    public double getCurrentForwardrateYlds(double timepoint_1, double timepoint_2) {

        return (Errorcheck(timepoint_1) == 1 & Errorcheck(timepoint_2) == 1) ? (forateFromyld(getCurrentSpotOne(timepoint_1), getCurrentSpotOne(timepoint_2), timepoint_1, timepoint_2)) : 0.0;
    }

    public double getCurrentForwardrateSpts(int period_1, int period_2) {
        double timepoint_1, timepoint_2;
        timepoint_1 = (double) period_1;
        timepoint_2 = (double) period_2;
        return (Errorcheck(timepoint_1) == 1 & Errorcheck(timepoint_2) == 1) ? forateFromspts(period_1, period_2) : 0.0;
    }

    /**
     * Implements the abstract method from abstract class Interms
     */
    public void Intermspots() {

        spotterms = currentdata;

    }

    /**
     * Method to provide simple error checking for range of data in yield array and to
     * check that current data is selected
     *
     * @return If checking is correct will return 1
     * @@param timepoint The requestor timepoint being selected for validity checking
     */
    public double creditSpread(double[] Pzero, double[] Prisky) {
        double[] pdefault;
        double[] fpdefault;
        return 1.0;
    }

    private int Errorcheck(double timepoint) {

        if (current_flag == 0) {
            System.out.println("Error:no data array found for yield");
            return 0;
        }
        int n = currentdata.length;
        if ((timepoint < currentdata[0][0]) || (timepoint > currentdata[n - 1][0])) {
            System.out.println("Error:time variable out of data range");
            return 0;
        }
        return 1;
    }

}

