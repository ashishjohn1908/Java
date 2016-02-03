package FinApps;

import static java.lang.Math.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:19:50
 * To change this template use File | Settings | File Templates.
 */
public abstract class Interms {
    double[][] spotterms;


    /**
     * Abstract method has to be implemented in calling class
     */
    public abstract void Intermspots();

    /**
     * provides discount rate given the spot rate
     *
     * @param spotrate spotrate of interest
     * @param time     the time (months/years) of the spot rate
     * @return the discount rate of interest
     */
    public double disFromyld(double spotrate, double time) {
        return Math.exp(-spotrate * time);
    }
    /** Provides the yield rate( the spot rate) of interest given the discount rate
     * @param discount the discount rate of interest
     * @param time the time for which discount is related
     * @return the yield rate of interest
     */
    /**
     * Method yldFromdisc
     *
     * @param discount a  double
     * @param time     a  double
     * @return a double
     * @version 4/7/2004
     */
    public double yldFromdisc(double discount, double time) {
        return -Math.log(discount) / time;
    }

    /**
     * forward rate of interest from spot rates
     *
     * @param timea time 1
     * @param timeb time 2 where time 2>time 1
     * @return forward rate of interest for times between spot1 and spot2, corresponding to timea and timeb
     */
    public double forateFromspts(int timea, int timeb)//where b>a in time
    {

        double num = exp(spotterms[timeb - 1][0] * log(1.0 + (spotterms[timeb - 1][1] / 100.0)));
        double denom = exp(spotterms[timea - 1][0] * log(1.0 + (spotterms[timea - 1][1] / 100.0)));

        return (pow((num / denom), (1.0 / (spotterms[timeb - 1][0] - spotterms[timea - 1][0]))) - 1);

    }

    /**
     * forward rate of interest from two period discount rates
     *
     * @param discount1 time 1 discount rate
     * @param discount2 time 2 discount rate
     * @param time1     the beginning forward rate period
     * @param time2     the end forward rate time
     * @return forward interest rate for the periods time1-time2
     */
    public double forateFromdisc(double discount1, double discount2, double time1, double time2) {
        return (Math.log(discount1 / discount2) / (time2 - time1));
    }

    /**
     * gives forward rates from yields
     *
     * @param r1 rate for period 1
     * @param r2 rate for period 2
     * @param t1 the time for start of forward period
     * @param t2 time for end of forward period
     * @return The forward interest rate for the period t1..t2
     */
    public double forateFromyld(double r1, double r2, double t1, double t2) {
        return (r2 * (t2 / (t2 - t1))) - (r1 * (t1 / (t2 - t1)));
    }


}

