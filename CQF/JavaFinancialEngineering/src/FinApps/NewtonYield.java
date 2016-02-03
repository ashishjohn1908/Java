package FinApps;

import BaseStats.inputmod;
import CoreMath.NewtonRaphson;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:55:54
 * To change this template use File | Settings | File Templates.
 */
public class NewtonYield extends NewtonRaphson {

    Data data = new Data();

    public double newtonroot(double rootinput) {
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
        public double precision;
        protected int iterations;
        protected double stockvalue;
        protected double nominalstockprice;
        protected double termperiod;
        protected double couponrate;
        protected double marketpricevalue;
        protected double initialapprox;
        protected double rateperTerm;
        protected double maturityperiod;

        public Data() {
        }

        public double getprecision() {

            return precision;
        }

        public void setprecision(double precision) {
            this.precision = precision;
        }

        public int getiterations() {

            return iterations;
        }

        public void setiterations(int iterations) {
            this.iterations = iterations;
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

        public double getinitialapprox() {
            return initialapprox;
        }

        public void setinitialapprox(double initialapprox) {
            this.initialapprox = initialapprox;
        }

        public void datahandler() //this method handles the user interface for application specific data//
        {
            double poscashflow;
            double solution;
            System.out.print("Enter the precision required");
            data.setprecision(inputmod.readDouble());
            System.out.print("Enter the Maximum iterations allowed");
            data.setiterations(inputmod.readInt());
            System.out.print("Enter the termperiod for payments out:");
            data.settermperiod(inputmod.readDouble());
            System.out.print("Enter the term to redemption(maturity period):");
            data.setmaturityperiod(inputmod.readDouble());
            System.out.print("Enter initial estimate: ");
            data.setinitialapprox(inputmod.readDouble());
            System.out.print("Enter the annual coupon rate");
            data.setcouponrate(inputmod.readDouble());
            System.out.print("Enter the market price for the bond");
            data.setmarketpricevalue(inputmod.readDouble());
            System.out.print("Enter the Nominal Stockprice");
            data.setnominalstockprice(inputmod.readDouble());

        }
    }

    public static void main(String[] args) {

        NewtonYield CalcBond = new NewtonYield();
        CalcBond.data.datahandler();
        CalcBond.accuracy(CalcBond.data.getprecision(), CalcBond.data.getiterations());
        System.out.println("RESULT  " + CalcBond.newtraph(CalcBond.data.getinitialapprox()));

    }
}




