package FinApps;

import CoreMath.NewtonRaphson;

import static java.lang.Math.pow;


/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:26:41
 * To change this template use File | Settings | File Templates.
 */
public class Tyield extends NewtonRaphson {
    private double precision = 1e-5;
    private int iterations = 20;
    private double nominalstockprice = 0.0;
    private double termperiod = 0.0;
    private double couponrate = 0.0;
    private double marketpricevalue = 0.0;
    private double firstestimate = 0.0;
    private double rateperAnum = 0.0;
    private double maturityperiod = 0.0;

    public Tyield() //default constructor//
    {

    }

    public Tyield(int nofiterations, double precision) {
        this.precision = precision;
        this.iterations = nofiterations;

    }


    public double yieldEstimate(double facevalue, double termtopay, double coupon, double marketprice, double maturity, double yieldestimate) {
        this.nominalstockprice = facevalue;
        this.termperiod = termtopay;
        this.couponrate = coupon;
        this.marketpricevalue = marketprice;
        //this.rateperAnum;
        this.maturityperiod = maturity;
        this.firstestimate = yieldestimate;
        accuracy(precision, iterations);//sets the accuracy method in super class
        return (((newtraph(yieldestimate)) * 12.0 / termtopay) * 100.0);// returns the annual yield as 12/the term ..== times per year * 1 year * 100 as %
    }


    public double newtonroot(double rootinput) //implements the abstract method from Derivative//
    {
        double poscashflow = 0.0;
        double solution = 0.0;
        rateperAnum = (nominalstockprice * (couponrate / 100));
        //annual cashflow out //
        poscashflow =
                rateperAnum * (termperiod / 12);
        //cashflow out per term as monthly amount * termperiod//
        solution =
                poscashflow
                        / rootinput
                        * (1 - 1 / (pow(1 + rootinput, (maturityperiod / (termperiod / 12)))))
                        + nominalstockprice / (pow(1 + rootinput, (maturityperiod / (termperiod / 12))))
                        - marketpricevalue;

        return solution;

    }

}

