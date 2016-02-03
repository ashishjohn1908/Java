package FinApps;

import static FinApps.PresentValue.pVs;
import static FinApps.PresentValue.pVw;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:32:22
 * To change this template use File | Settings | File Templates.
 */
public class Duration {

    /**
     * Creates a new instance of Duration
     */
    public Duration() {
        this.frequency = 2.0;

    }

    public Duration(double couponfreq) {
        this.frequency = couponfreq;

    }

    private void setMDyears(double mdperiods) {
        this.mdyrs = mdperiods / frequency;
    }

    public double getMDyears() {
        return mdyrs;
    }

    public double getMDmodyrs() {
        return modmdyrs;
    }

    private void setMDmodyrs(double mdyears, double discvalue) {
        this.modmdyrs = mdyears / discvalue;
    }

    private void setDduration(double modurationyrs, double price) {
        this.dolduration = ((modurationyrs * price) / 1e4);

    }

    public double getDolduration() {
        return dolduration;
    }

    private void setPerchange(double moduration) {
        this.percentchange = -moduration;
    }

    public double getPerchange(double basispoints) {
        return 100 * (percentchange * basispoints);
    }

    private double percentchange;
    private double dolduration;
    private double modmdyrs;
    private double mdyrs;
    private double frequency;

    /**
     * Requires the yield and coupon as a decimal value
     */
    public double duration(double yield, double period, double parprice, double coupon) {

        double val = 0;
        Volatility v = new Volatility(parprice, frequency);
        double bondprice = v.Bpricing((yield * 100.0), period, (coupon * 100.0));//require yield and coupon as a percentage
        yield = yield / frequency;
        coupon = coupon / frequency;
        int n = (int) (period * frequency);
        val = (n * (pVs(yield, parprice, n) / bondprice));// face value discounted..
        for (int i = 1; i < (n + 1); i++) {
            double value = ((pVw(yield, (coupon * parprice), i)) / bondprice);
            val += ((pVw(yield, (coupon * parprice), i)) / bondprice);// individual period cash flows

        }
        setMDyears(val);
        setMDmodyrs(getMDyears(), (1 + yield));
        setDduration(getMDmodyrs(), bondprice);
        setPerchange(getMDmodyrs());
        return val;
    }

}
