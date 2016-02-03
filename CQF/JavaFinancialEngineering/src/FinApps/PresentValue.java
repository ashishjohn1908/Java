package FinApps;

import static FinApps.Intr.conintr;

import static java.lang.Math.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:14:07
 * To change this template use File | Settings | File Templates.
 */
public final class PresentValue {

    /**
     * Creates a new instance of PresentValue
     */
    public PresentValue() {
    }

    /**
     * Present value of a series of cashflows (as decimal values .. $10.00x )for a series of  rates
     *
     * @param discounts array of rates used in the discount as percentages
     * @param cashflows array of cashflows
     * @return present value of the sum
     */
    public static double pV(double[] discounts, double[] cashflows) {
        int n = cashflows.length;
        double presval = 0;
        for (int i = 0; i < n; i++) {


            presval += (exp(-(i + 1) * log(1.0 + (discounts[i] / 100.0))) * cashflows[i]);

        }
        return presval;
    }

    public static double pVcont(double rate, double years, double amount) {
        return (conintr(-rate, years) * amount);
    }

    public static double pVcont(double rate, double years) {
        return conintr(-rate, years);
    }

    public static double pVonecash(double[] discounts, double cashflow) {
        int i = 0;
        double presval = 0;
        for (double d : discounts) {

            presval += (exp(-(i + 1) * log(1.0 + (d / 100.0))) * cashflow);

            i++;
        }
        return presval;
    }

    /**
     * Present value of a series of arbitrary cashflows for given interest rate(single)
     *
     * @param r         the single interest rate as a decimal value
     * @param cashflows series of cashflows to be discounted
     * @return present value of discounted flows
     */
    public static double pV(double r, double[] cashflows) {
        int indx = 1;
        double sum = 0;
        for (int i = 0; i < cashflows.length; i++) {
            sum += (cashflows[i] * (exp(-(double) indx * log(1.0 + r))));

            indx++;
        }
        return sum;
    }

    /**
     * Present value of a series of single payments in arrears for given interest rate(single)
     *
     * @param r      the single interest rate as decimal
     * @param cash   series of single payments to be discounted
     * @param period the number of payments
     * @return present value of discounted flows
     */

    public static double pV(double r, double cash, int period) {
        double sum = 0;
        int indx = 1;
        for (int i = 0; i < period; i++) {
            sum += (cash * (exp(-(double) indx * log(1.0 + r))));
            // sum+=(cash/(pow((1+r),(indx))));
            indx++;

        }
        return sum;
    }


    public static double pVw(double r, double cash, int period)/* returns weighted discounted value */ {


        return (period * cash * (exp(-(double) period * log(1.0 + r))));
    }

    public static double pVs(double r, double cash, int period)/* returns simple single discounted value */ {


        return (cash * (exp(-(double) period * log(1.0 + r))));
    }

    /**
     * @param r
     * @param period
     * @return
     */
    public static double pV(double r, double period)// pv of a sum of == payments for n fractional periods
    {// suitable for short periods..<20
        double sum = 0;
        double periodoffset;

        int i;
        i = (int) period;

        periodoffset = period - i;
        int indx;
        indx = periodoffset == 0.0 ? 1 : 0;
        for (i = 0; i < period; i++) {
            sum += (1.0 / (pow((1.0 + r), (indx + periodoffset))));
            indx++;
        }
        return sum;
    }
}

