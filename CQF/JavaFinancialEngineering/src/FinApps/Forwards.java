package FinApps;

import static FinApps.Intr.conintr;
import static FinApps.PresentValue.pVcont;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:33:54
 * To change this template use File | Settings | File Templates.
 */
public final class Forwards {

    /**
     * Creates a new instance of Forwards
     */
    public Forwards() {

    }


    /**
     * method to return the dollar intersest value coefficint for the term of a repo rate
     *
     * @param term     is the term in years (as decimal) greater than 1 day
     * @param reporate the current bank base rate/ federal funds rate
     */
    public static double dollarIntr(double term, double reporate) {
        return reporate * (term / 360.0);
    }

    /**
     * method to return the delivery price of a new  forward contract
     *
     * @param spotprice is the spot price of the underlying asset
     * @param maturity  is the time (in years as a decimal) to maturity of the contract
     * @@param currentime is the start time of the new contract
     */
    public static double delpriceNoinc(double spotprice, double maturity, double reporate) {
        return (spotprice * conintr(reporate, maturity));
    }

    public static double fpriceNoinc(double spotprice, double maturity, double currentime, double deliveryprice, double reporate) {
        return (spotprice - (pVcont(reporate, (maturity - currentime), deliveryprice)));
    }

    public static double fpriceInc(double spotprice, double maturity, double currentime, double reporate, double period, double dividend) {
        double income = 0.0;
        income = maturity == 1.0 ? pVcont(reporate, 1.0, dividend) : 0.0;//last value
        double limit = 0.0;

        limit = (maturity - currentime);//Assumes that later start times will floor the pv of dividend payments
        double time = (period / 12.0);
        double increment = time;
        while (time < limit) {
            income += pVcont(reporate, time, dividend);

            time = time + increment;

        }

        return ((spotprice - income) * (conintr(reporate, (maturity - currentime))));

    }


    public static double fpriceInc(double spotprice, double maturity, double currentime, double[] reporate, double period, double dividend) {
        double income = 0.0;
        double limit = 0.0;
        double forwardprice = 0.0;
        limit = (maturity - currentime);//Assumes that later start times will floor the pv of dividend payments
        double time = (period / 12.0);
        double increment = time;
        for (double r : reporate) {
            income += pVcont(r, time, dividend);
            time = time + increment;

        }


        return ((spotprice - income) * (conintr(reporate[(reporate.length - 1)], (maturity - currentime))));

    }


    public static double fvalueInc(double spotprice, double maturity, double currentime, double reporate, double period, double dividend, double deliveryprice) {
        double income = 0.0;
        income = maturity == 1.0 ? pVcont(reporate, 1.0, dividend) : 0.0;//last value
        double limit = 0.0;

        limit = (maturity - currentime);//Assumes that later start times will floor the pv of dividend payments
        double time = (period / 12.0);
        double increment = time;
        while (time < limit) {
            income += pVcont(reporate, time, dividend);

            time = time + increment;

        }

        return ((spotprice - income) - (deliveryprice * pVcont(reporate, (maturity - currentime))));
    }

    public static double fvalueInc(double spotprice, double maturity, double currentime, double[] reporate, double period, double dividend, double deliveryprice) {
        double income = 0.0;
        double forwardprice = 0.0;
        double time = (period / 12.0);
        double increment = time;
        for (double r : reporate) {
            income += pVcont(r, time, dividend);
            time = time + increment;


        }


        return (spotprice - (income + (deliveryprice * pVcont(reporate[(reporate.length - 1)], (maturity - currentime)))));
    }
    // Also parity rate calculation

    public static double fvaluegen(double fprice, double delivprice, double maturity, double currentime, double reporate) {


        return ((fprice - delivprice) * pVcont(reporate, (maturity - currentime)));

    }

    public static double fpriceDyld(double spotprice, double maturity, double currentime, double reporate, double dividendyld) {
        return (spotprice * conintr((reporate - dividendyld), (maturity - currentime)));
    }

    public static double fvalueDyld(double spotprice, double maturity, double currentime, double reporate, double dividendyld, double deliveryprice)

    {
        return ((fpriceDyld(spotprice, maturity, currentime, reporate, dividendyld) - (deliveryprice)) * pVcont(reporate, (maturity - currentime)));
    }
}
