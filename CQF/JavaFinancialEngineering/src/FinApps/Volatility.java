package FinApps;

import static FinApps.Intr.pvancert;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:23:21
 * To change this template use File | Settings | File Templates.
 */
public class Volatility {

    /**
     * Creates a new instance of Volatility
     */
    public Volatility() {
        this.facevalue = 1000.0;
        this.frequency = 2.0;
    }

    public Volatility(double parvalue, double coupontimes) {
        this.facevalue = parvalue;
        this.frequency = coupontimes;
    }

    private double mktprice;
    private double mktpricelow;
    private double mktpricenew;
    private double couponvalue;//couponvalue = par value*annual coupon percent/2
    private double facevalue;
    private double frequency;
    private double pv;
    private double par;
    private double relativeprice;
    private double relativepricelow;
    private double upyield;
    private double downyield;
    private double newpriceup;
    private double newpricedown;
    private double currentyield;
    private double currentpvb;

    private void setInitialYldPp(double yield) {
        currentyield = yield;
    }

    public double getInitialPpYld() {
        return currentyield;
    }

    private void setPpointpriceup(double price) {
        newpriceup = price;
    }

    private void setPpointpricedown(double price) {
        newpricedown = price;
    }

    public double getPriceupPp() {
        return newpriceup;
    }

    public double getPricedownPp() {
        return newpricedown;
    }

    private void setdownyieldPp(double yield) {
        downyield = yield;
    }

    private void setupyieldPp(double yield) {
        upyield = yield;
    }

    public double getUpPp() {
        return upyield;
    }

    public double getDownPp() {
        return downyield;
    }

    public double getValuePUp() {
        return (abs(getUpPp() - getInitialPpYld()) / 100.0);
    }

    public double getValuePDown() {
        return (abs(getDownPp() - getInitialPpYld()) / 100.0);
    }

    private void setRelativeValue(double price) {
        relativeprice = price;
    }

    public double getRelativeValue() {
        return relativeprice;
    }

    private void setRelativeValuelow(double price) {
        relativepricelow = price;
    }

    public double getRelativeValuelow() {
        return relativepricelow;
    }

    private void setCurrentPvb(double price) {
        currentpvb = price;
    }

    public double getCurrentPvb() {
        return currentpvb;
    }


    /* Price value of a basis point */
    public void pVbPoints(double yield, double yearterm, double coupon, double pointchange) {

        double yieldval;
        mktprice = Bpricing(yield, yearterm, coupon);
        setCurrentPvb(mktprice);
        yieldval = (yield + (pointchange / 100.0));// make basis point adjustment higher
        mktpricenew = Bpricing(yieldval, yearterm, coupon);
        setRelativeValue(abs(mktpricenew - mktprice));
        yieldval = (yield - (pointchange / 100.0));// make basis point adjustment lower
        mktpricelow = Bpricing(yieldval, yearterm, coupon);
        setRelativeValuelow(abs(mktpricelow - mktprice));


    }

    public double Bpricing(double yield, double yearterm, double coupon) {
        couponvalue = ((facevalue * coupon / 100) / frequency);
        pv = (couponvalue * pvancert((yield / 100.0) / frequency, (frequency * yearterm)));
        par = (facevalue * (1.0 / pow(1.0 + (yield / 100.0) / frequency, (frequency * yearterm))));
        return (pv + par);
    }


    /**
     * Spricing method requires rates for each compounding period and the annual coupon rate
     */

    public double percentVolatility(double yield, double yearterm, double coupon, double pointchange) {
        pVbPoints(yield, yearterm, coupon, pointchange);//price value of a basis point
        mktprice = Bpricing(yield, yearterm, coupon);
        return ((getRelativeValue() / mktprice) * 100.0);

    }

    /**
     * Method provides yield values for a percentage point change in par value
     * Sets via accessor methods the Yield value of a point change, the initial yield value prior to applying the point change
     */
    public void yieldForPpoint(double couponpercent, double price, double maturity, double estimate, double pointvalue) {

        double couponterm = 12.0 / frequency;
        double change = ((facevalue / 100.0) * pointvalue);
        setPpointpricedown(price - change);
        setPpointpriceup(price + change);
        Tyield CalcBond = new Tyield();
        setInitialYldPp(CalcBond.yieldEstimate(facevalue, couponterm, couponpercent, price, maturity, estimate));
        setdownyieldPp(abs((CalcBond.yieldEstimate(facevalue, couponterm, couponpercent, getPricedownPp(), maturity, estimate))));
        setupyieldPp(abs((CalcBond.yieldEstimate(facevalue, couponterm, couponpercent, getPriceupPp(), maturity, estimate))));


    }

}

