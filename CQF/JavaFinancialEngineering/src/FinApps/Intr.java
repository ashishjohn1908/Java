package FinApps;

import static java.lang.Math.exp;
import static java.lang.Math.log;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:16:39
 * To change this template use File | Settings | File Templates.
 */
public final class Intr {

    /**
     * Creates a new instance of Class
     */
    public Intr() {


    }


    /**
     * Real interest calculator
     *
     * @param nintr nominal interest as input
     * @return the calculated real interest
     */
    public static double realintr(double nintr, double cl0, double cl1) {
        return 100 * ((cl0 * (1 + nintr) / cl1) - 1.0); //real interest as cost of living adjusted index
    }

    /**
     * Continuos interest rate calculator
     *
     * @param rate  the interest rate applicable for continuos compounding
     * @param years the time to compound
     * @return the compound rate
     */
    public static double conintr(double rate, double years) {
        return exp(rate * years);
    }

    /**
     * Continuos interest calculator converts discrete to continuos period
     *
     * @param periods the number of discrete periods per year
     *                *param rate the interest rate as a decimal
     * @ return the continuosly compounded equivallent rate
     */
    public static double discrtocont(double periods, double rate) {
        return (periods * (log(1.0 + (rate / periods))));
    }

    /**
     * Continuos interest calculator converts continuos period to discrete
     *
     * @param periods the discrete rate period required
     *                *param rate the interest rate as a decimal
     * @ return the continuosly compounded equivallent rate
     */
    public static double contodiscr(double periods, double rate) {
        return (periods * (exp(rate / periods) - 1.0));
    }

    /**
     * computes effective annual interest rate for p conversions annually
     *
     * @param intr     the input annual interest rate
     * @param convertp the number of conversions per annum
     * @return the effective rate
     */
    public static double erate(double intr, double convertp) {
        return Math.pow((1 + (intr / convertp)), convertp) - 1;//efective interest as an annual rate being converted p times annually

    }

    /**
     * Computes the force of interest-continuos compounding
     *
     * @param intr the input interest rate to be compounded
     * @return the force of interest
     */
    public static double fint(double intr) {
        return Math.log(1 + intr);// force of interest ..as continuosly compounded interest
    }

    /**
     * annuity certain- cumulative amounts annually-in arrears
     *
     * @param intr the input interest rate
     * @param n    the number of payments per annum
     * @return the multiplier rate
     */
    public static double ancertain(double intr, double n) {
        return ((Math.pow((1 + intr), n) - 1) / intr);// annuity certain..cumulative amount for a series of payments at n ints at a given int rate
        // payed in arrears
    }

    /**
     * computes an annuity certain-series of payments in advance
     *
     * @param intr input interest rate
     * @param n    number of conversion periods per annum
     * @return the multiplier rate
     */
    public static double ancertainAd(double intr, double n) {
        return (((Math.pow((1 + intr), n) - 1) / intr) * (1 + intr));// annuity certain..cumulative amount for a series of payments at n ints at a given int rate
        // payed in advance
    }

    /**
     * present value of an annuity certain --in arrears
     *
     * @param intr the input interest rate per conversion period
     * @param n    number of conversions
     * @return the multiplier rate
     */
    public static double pvancert(double intr, double n) {
        return (1.0 - (1 / Math.pow((1 + intr), n))) / intr;     // PV of an annuity certain
        // payed in arrears
    }

    /**
     * present value of an annuity certain in advance
     *
     * @param intr the input interest rate
     * @param n    the number of conversions per annum * years
     * @return The multiplier rate
     */
    public static double pvancertAd(double intr, double n) {
        return ((1 + intr) * (1.0 - (1 / Math.pow((1 + intr), n))) / intr);  //PV of an annuity certain in advance

    }

    /**
     * present value of an infinite number of payments with each payment growing 1+g in
     * succession
     *
     * @param intr   the interest rate (flat)
     * @param growth the growth rate between payments
     * @param value  initial value of payment
     * @return the present value for the interest rate
     */
    public static double pvainfprog(double intr, double growth, double value) {
        return value / intr - growth;//pv of an ininite number of payments od d each /each being grown by 1+g (multiplier)
        //if growth =0, this is a perpetuity
    }

    /**
     * computes the present value of an annuity with successive payments a multiple
     *
     * @param intr the input flat rate of interest
     * @param n    the growth multiplier
     * @return present value of the annuity
     */
    public static double pvanmult(double intr, double n) {
        double value = 1 / (1 + intr);
        return ((pvancertAd(intr, n)) - (n * Math.pow(value, n))) / intr;//pv of an increasing annuity with each period being a multiple of n per period

    }

    /**
     * Computes Effective present interest rate
     *
     * @param annualintr The effective annual interest rate
     * @param p          the present flat rate
     * @return the effective present rate
     */
    public static double effectintp(double annualintr, double p) {
        return Math.pow((1 + annualintr), (1 / p)) - 1;//given the effective annual int rate gives the effective p rate
    }

    /**
     * computes the effective annaul rate for a present rate
     *
     * @param nomnualintr the nominal interest rate
     * @param p           the present rate
     * @return the effective annaul rate
     */
    public static double effectann(double nomnualintr, double p) {

        return (Math.pow((1 + nomnualintr / p), p) - 1);//given a nominal rate returns the effctive annual rate for p
    }
}

