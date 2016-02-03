package FinApps;

import java.util.Calendar;
import static java.util.Calendar.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:30:43
 * To change this template use File | Settings | File Templates.
 */
public class Accruedconvention {

    /**
     * Creates a new instance of Daycounts according to the convention
     */
    //Default constructor assumes 2*6 monthly coupon payments...semi annual
    public Accruedconvention() {
        this.coupons = 2.0;
    }

    public Accruedconvention(double couponperiod)// non default constructor to set the coupon period monthly times per annum
    {
        this.coupons = 12.0 / couponperiod;
    }

    public double getPreviousCoupondays() {
        return previouscoupondays;
    }

    private void setPreviousCoupondays(double prevcoupdate) {
        this.previouscoupondays = prevcoupdate;
    }

    Calendar previouscoupon = Calendar.getInstance();
    private double coupons;
    public double previouscoupondays;

    public double daycounts(int flagvalue, Calendar settlementdate, Calendar nextcoupondate) {
        Calendar temp = Calendar.getInstance();
        previouscoupon.set(YEAR, (nextcoupondate.get(YEAR)));
        previouscoupon.set(MONTH, (nextcoupondate.get(MONTH) - 6));// assumes default 6 monthly period
        previouscoupon.set(DATE, (nextcoupondate.get(DATE)));
        int actualday = 0;
        int actualdays = 0;
        int samedays = 0;
        switch (flagvalue) {

            case 1:
                if (settlementdate.get(MONTH) == nextcoupondate.get(MONTH))//Actual/actual in period (eg, US gov)
                {
                    samedays = (nextcoupondate.get(DATE) - settlementdate.get(DATE));
                    for (int n = (previouscoupon.get(MONTH) + 1); n < nextcoupondate.get(MONTH); n++) {
                        temp.set(MONTH, n);
                        actualdays += temp.getActualMaximum(DAY_OF_MONTH);
                    }
                    actualdays += (previouscoupon.getActualMaximum(DAY_OF_MONTH) - previouscoupon.get(DATE));
                    setPreviousCoupondays((double) (actualdays - samedays) / (actualdays));
                    return (double) samedays / actualdays;
                }

                int setdays = (settlementdate.getActualMaximum(DAY_OF_MONTH) - settlementdate.get(DATE));
                actualday = setdays;

                for (int i = (settlementdate.get(MONTH) + 1); i < nextcoupondate.get(MONTH); i++) {
                    temp.set(MONTH, i);
                    actualday += temp.getActualMaximum(DAY_OF_MONTH);

                }
                actualday += nextcoupondate.get(DATE);
                actualdays = nextcoupondate.get(DATE);
                temp.clear();
                for (int n = (previouscoupon.get(MONTH) + 1); n < nextcoupondate.get(MONTH); n++) {
                    temp.set(MONTH, n);
                    actualdays += temp.getActualMaximum(DAY_OF_MONTH);
                }
                actualdays += (previouscoupon.getActualMaximum(DAY_OF_MONTH) - previouscoupon.get(DATE));
                setPreviousCoupondays((double) (actualdays - actualday) / (actualdays));
                return (double) actualday / actualdays; //returns fraction of the coupon period


            case 2:
                for (int n = (previouscoupon.get(MONTH) + 1); n < settlementdate.get(MONTH); n++) {
                    temp.set(MONTH, n);
                    actualdays += temp.getActualMaximum(DAY_OF_MONTH);
                }
                actualdays += (previouscoupon.getActualMaximum(DAY_OF_MONTH) - previouscoupon.get(DATE));
                actualdays += settlementdate.get(DATE);

                if (settlementdate.get(MONTH) == nextcoupondate.get(MONTH))//Actual/365 (eg,UK gov)
                {

                    samedays = (nextcoupondate.get(DATE) - settlementdate.get(DATE));
                    setPreviousCoupondays((double) (((365.0 / coupons) - samedays) / (365.0 / coupons)));//requires annual multiple of coupon rate
                    return (double) (samedays / (365.0 / coupons));//returns 1/365 ths of the annual coupon rate
                }

                actualday = (settlementdate.getActualMaximum(DAY_OF_MONTH) - settlementdate.get(DATE));

                for (int i = (settlementdate.get(MONTH) + 1); i < nextcoupondate.get(MONTH); i++) {
                    temp.set(MONTH, i);
                    actualday += temp.getActualMaximum(DAY_OF_MONTH);

                }
                actualday += nextcoupondate.get(DATE);
                System.out.println("Actual days between coupon and settlement ==" + actualdays);
                setPreviousCoupondays((double) (actualdays / (365.0 / coupons)));
                return (double) (((365.0 / coupons) - actualdays) / (365.0 / coupons));


            case 3:
                for (int n = (previouscoupon.get(MONTH) + 1); n < settlementdate.get(MONTH); n++) {
                    temp.set(MONTH, n);
                    actualdays += temp.getActualMaximum(DAY_OF_MONTH);
                }
                actualdays += (previouscoupon.getActualMaximum(DAY_OF_MONTH) - previouscoupon.get(DATE));
                actualdays += settlementdate.get(DATE);

                if (settlementdate.get(MONTH) == nextcoupondate.get(MONTH))//Actual/365 or 366 in leap year  (eg,
                {
                    samedays = (nextcoupondate.get(DATE) - settlementdate.get(DATE));
                    int total;
                    total = (previouscoupon.getActualMaximum(DAY_OF_YEAR) | nextcoupondate.getActualMaximum(DAY_OF_YEAR)) == 366 ? 366 : 365;
                    setPreviousCoupondays((double) (((total / coupons) - samedays) / (total / coupons)));
                    return (double) samedays / (total / coupons);
                }

                actualday = (settlementdate.getActualMaximum(DAY_OF_MONTH) - settlementdate.get(DATE));

                for (int i = (settlementdate.get(MONTH) + 1); i < nextcoupondate.get(MONTH); i++) {
                    temp.set(MONTH, i);
                    actualday += temp.getActualMaximum(DAY_OF_MONTH);

                }
                actualday += nextcoupondate.get(DATE);
                int totaldays;
                totaldays = (previouscoupon.getActualMaximum(DAY_OF_YEAR) | nextcoupondate.getActualMaximum(DAY_OF_YEAR)) == 366 ? 366 : 365;

                System.out.println("Actual days between coupon and settlement ==" + actualdays);
                setPreviousCoupondays((double) (actualdays / (totaldays / coupons)));
                return (double) (((totaldays / coupons) - actualdays) / (totaldays / coupons));
            case 4:

                if (settlementdate.get(MONTH) == nextcoupondate.get(MONTH))// Coupon annual eg US Gov't agency..
                {
                    samedays = (nextcoupondate.get(DATE) - settlementdate.get(DATE));
                    setPreviousCoupondays((double) ((360.0) - samedays) / (360.0));
                    return (double) samedays / (360.0);
                }
                actualday = (settlementdate.getActualMaximum(DAY_OF_MONTH) - settlementdate.get(DATE));

                for (int i = (settlementdate.get(MONTH) + 1); i < nextcoupondate.get(MONTH); i++) {
                    temp.set(MONTH, i);
                    actualday += temp.getActualMaximum(DAY_OF_MONTH);

                }
                actualday += nextcoupondate.get(DATE);
                setPreviousCoupondays((double) ((360.0) - actualday) / (360.0));
                return (double) actualday / (360.0);
            case 5:

                if (settlementdate.get(MONTH) == nextcoupondate.get(MONTH))//30/360..one day only..(eg,US corporate)
                {
                    int numsetd = settlementdate.get(DATE);
                    numsetd = numsetd == 31 ? 30 : numsetd;
                    int numd = nextcoupondate.get(DATE);

                    numd = ((numd == 31) & (numsetd == 30)) ? 30 : numd;
                    samedays = numd - numsetd;
                    samedays = (nextcoupondate.get(DATE) - settlementdate.get(DATE));
                    samedays = samedays == 31 ? 30 : samedays;
                    setPreviousCoupondays((double) ((360.0 / coupons) - samedays) / (360.0 / coupons));
                    return (double) samedays / (360.0 / coupons);
                }
                int couponset;
                int dayset = settlementdate.getActualMaximum(DAY_OF_MONTH);
                int dateset = settlementdate.get(DATE);
                dayset = dayset == 31 ? 30 : dayset;
                dateset = dateset == 31 ? 30 : dateset;
                actualday = dayset - dateset;
                for (int i = (settlementdate.get(MONTH) + 1); i < nextcoupondate.get(MONTH); i++) {
                    temp.set(MONTH, i);
                    couponset = temp.getActualMaximum(DAY_OF_MONTH);
                    couponset = ((couponset == 31) & (dayset == 30)) ? 30 : couponset;
                    actualday += couponset;

                }
                int coupdate = nextcoupondate.get(DATE);
                coupdate = ((coupdate == 31) & (dateset == 30)) ? 30 : coupdate;
                actualday += coupdate;
                setPreviousCoupondays((double) ((360.0 / coupons) - actualday) / (360.0 / coupons));
                return (double) actualday / (360.0 / coupons);


            case 6:

                if (settlementdate.get(MONTH) == nextcoupondate.get(MONTH))//30e/360
                {
                    int numdays = nextcoupondate.get(DATE);
                    numdays = numdays == 31 ? 30 : numdays;
                    int numsetdays = settlementdate.get(DATE);
                    numsetdays = numsetdays == 31 ? 30 : numsetdays;
                    samedays = numdays - numsetdays;
                    setPreviousCoupondays((double) ((360.0 / coupons) - samedays) / (360.0 / coupons));
                    return (double) samedays / (360.0 / coupons);
                }
                int couponsettle;
                int daysettle = settlementdate.getActualMaximum(DAY_OF_MONTH);
                int datesettle = settlementdate.get(DATE);
                daysettle = daysettle == 31 ? 30 : daysettle;
                datesettle = datesettle == 31 ? 30 : datesettle;
                actualday = daysettle - datesettle;
                for (int i = (settlementdate.get(MONTH) + 1); i < nextcoupondate.get(MONTH); i++) {

                    temp.set(MONTH, i);
                    couponsettle = temp.getActualMaximum(DAY_OF_MONTH);
                    couponsettle = couponsettle == 31 ? 30 : couponsettle;
                    actualday += couponsettle;

                }
                int coupondate = nextcoupondate.get(DATE);
                coupondate = coupondate == 31 ? 30 : coupondate;
                actualday += coupondate;
                System.out.println("actualdays  " + actualday);
                setPreviousCoupondays((double) ((360.0 / coupons) - actualday) / (360.0 / coupons));
                return (double) actualday / (360.0 / coupons);

            default:
                throw new AssertionError("Unknown market :" + flagvalue);

        }

    }
}
