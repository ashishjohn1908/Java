package FinApps;

import static java.lang.Math.*;
import CoreMath.Csmallnumber;
import BaseStats.Probnorm;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 23:11:35
 * To change this template use File | Settings | File Templates.
 */
public class Lnormprice {
    public Lnormprice() {
        conflevel = 1.0;
    }

    public Lnormprice(double confidence) {
        conflevel = confidence;
    }

    private double conflevel;
    private double pdf;
    private double cdf;
    private double vaverage;
    private double vsd;
    private double[] range = new double[2];
    private double[] retrange = new double[2];

    private void setPdf(double pdfvalue) {
        pdf = pdfvalue;
    }

    private void setCdf(double cdfvalue)//P(X>x)
    {
        cdf = cdfvalue;
    }

    public double getPdf() {
        return pdf;
    }

    public double getCdf() {
        return cdf;
    }

    private void setVaverage(double average) {
        vaverage = average;
    }

    private void setSd(double sd) {
        vsd = sd;
    }

    public double getAverage() {
        return vaverage;
    }

    public double getSd() {
        return vsd;
    }

    public double[] getRange() {
        return range;
    }

    public double[] getRetrange() {
        return retrange;
    }

    public void logprice(double So, double St, double mulog, double
            sdlog, double t) {
        Probnorm p = new Probnorm();
        double meanval = (log(So) + ((mulog - (pow(sdlog, 2.0) * 0.5)) * t));
        setSd((sdlog * sqrt(t)));
//sets a variance value
        setVaverage(meanval);
        double sdlevel = (getSd() * conflevel);
        range[0] = exp((getAverage() - sdlevel));
        range[1] = exp((getAverage() + sdlevel));
        setCdf(p.ncDisfnc((log(St) - getAverage()) / getSd()));
        double divisor = 0.0;
        divisor = (sqrt(2 * PI));
        divisor = (1.0 / (divisor * getSd() * St));
        Double testval = new Double(divisor);
        divisor = testval.isInfinite() ? Csmallnumber.
                getSmallnumber() : divisor;
        setPdf(floorvalue((exp(-0.5 * pow(((log(St) - getAverage()) /
                getSd()), 2))) * divisor));
    }

    public void returnrate(double exreturn, double volatility,
                           double time) {
        double mean = (exreturn - (pow(volatility, 2.0) * 0.5));
        double sd = (volatility / sqrt(time));
        retrange[0] = ((mean - (conflevel * sd)) * 100.0);
        retrange[1] = ((mean + (conflevel * sd)) * 100.0);
    }

    public double floorvalue(double x) {
        return abs(x) < Csmallnumber.getSmallnumber() ? Csmallnumber.
                getSmallnumber() : x;
    }
}
