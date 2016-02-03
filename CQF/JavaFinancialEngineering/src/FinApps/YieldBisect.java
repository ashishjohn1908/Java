package FinApps;

import BaseStats.inputmod;
import CoreMath.IntervalBisection;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:24:34
 * To change this template use File | Settings | File Templates.
 */
//All of the input values and parametrs required for calculating the Gross Redemption Yield for a fixed cashflow per period//
public class YieldBisect extends IntervalBisection {
    public YieldBisect() //default constructor//
    {
    }

    public YieldBisect(int Nofiterations, double Precision) {
        super(Nofiterations, Precision);//alternate constructor with changed values for precision and number of iterations Interval Bisection//
    }

    //create the data object//
    Data data = new Data();

    public double computeFunction(double rootinput)//implements the abstract method from interval bisection
    {

        double poscashflow, solution, rateindex;
        data.setrateperTerm((data.getnominalstockprice() * (data.getcouponrate() / 100)));
        //annual cashflow out //
        rateindex = (data.getmaturityperiod() * 12 / data.gettermperiod());
        poscashflow = data.getrateperTerm() * (data.gettermperiod() / 12);
        //cashflow out per term as monthly amount * termperiod//
        solution = poscashflow / rootinput * (1 - 1 / (Math.pow(1 + rootinput, rateindex)))
                + data.getnominalstockprice() / (Math.pow(1 + rootinput, rateindex))
                - data.getmarketpricevalue();

        return solution;

    }

    //The data class handles input/output and storage of the application specific equation//
    class Data {
        protected double stockvalue;
        protected double nominalstockprice;
        protected double termperiod;
        protected double couponrate;
        protected double marketpricevalue;
        protected double inputvaluelow;
        protected double inputvaluehigh;
        protected double rateperTerm;
        protected double maturityperiod;

        public Data() {
        }

        public double getmaturityperiod() {
            return maturityperiod;
        }

        public void setmaturityperiod(double maturityperiod) {
            this.maturityperiod = maturityperiod;
        }

        public double getcouponrate() {
            return couponrate;
        }

        public void setcouponrate(double couponrate) {
            this.couponrate = couponrate;
        }

        public double getrateperTerm() {
            return rateperTerm;
        }

        public void setrateperTerm(double rateperTerm) {
            this.rateperTerm = rateperTerm;
        }

        public void settermperiod(double termperiod) {
            this.termperiod = termperiod;
        }

        public double gettermperiod() {
            return termperiod;
        }

        public void setstockvalue(double stockvalue) {
            this.stockvalue = stockvalue;
        }

        public double getstockvalue() {
            return stockvalue;
        }

        public void setnominalstockprice(double nominalstockprice) {
            this.nominalstockprice = nominalstockprice;
        }

        public double getnominalstockprice() {
            return nominalstockprice;
        }

        public void setmarketpricevalue(double marketpricevalue) {
            this.marketpricevalue = marketpricevalue;
        }

        public double getmarketpricevalue() {
            return marketpricevalue;
        }

        public double getinputvaluelow() {
            return inputvaluelow;
        }

        public void setinputvaluelow(double inputvaluelow) {
            this.inputvaluelow = inputvaluelow;
        }

        public double getinputvaluehigh() {
            return inputvaluehigh;
        }

        public void setinputvaluehigh(double inputvaluehigh) {
            this.inputvaluehigh = inputvaluehigh;
        }

        public void Datahandler() //this method handles the user interface for application specific data//
        {
            double poscashflow;
            double solution;
            System.out.print("Enter the termperiod for  interest payments :");
            data.settermperiod(inputmod.readDouble());
            System.out.print("Enter the term to redemption(maturity period):");
            data.setmaturityperiod(inputmod.readDouble());
            System.out.print("Enter low estimate: ");
            data.setinputvaluelow(inputmod.readDouble());
            System.out.print("Enter high estimate:");
            data.setinputvaluehigh(inputmod.readDouble());
            System.out.print("Enter the annual coupon rate");
            data.setcouponrate(inputmod.readDouble());
            System.out.print("Enter the market price for the investment");
            data.setmarketpricevalue(inputmod.readDouble());
            System.out.print("Enter the Nominal Stockprice");
            data.setnominalstockprice(inputmod.readDouble());
        }
    }

    public static void main(String[] args) {

        YieldBisect CalcBond = new YieldBisect();
        CalcBond.data.Datahandler();
        CalcBond.evaluateRoot(CalcBond.data.getinputvaluelow(), CalcBond.data.getinputvaluehigh());

    }
}




