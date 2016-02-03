package CoreMath;

import static java.lang.Math.sqrt;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:49:04
 * To change this template use File | Settings | File Templates.
 */
public class Itoprocess {

    /**
     * Creates a new instance of Itoprocess
     */
    public Itoprocess() {
    }

    private double sdchange;
    private double meanvalue;
    private double changebase;

    private void setChange(double changevalue) {
        changebase = changevalue;
    }

    public double getBaseval() {
        return changebase;
    }

    private void setSd(double sd) {
        sdchange = sd;
    }

    public double getSd() {
        return sdchange;
    }

    private void setMean(double drift) {
        meanvalue = drift;
    }

    public double getMean() {
        return meanvalue;
    }

    /**
     * @param mu        mean value
     * @param sigma     The variance
     * @param timedelta time periods for each step
     * @param basevalue the starting value
     * @return The change in the base value
     */
    public double itoValue(double mu, double sigma, double timedelta, double basevalue) {

        setSd(basevalue * (sigma * sqrt(timedelta)));
        Genwiener g = new Genwiener();
        mu = mu * basevalue;
        sigma = sigma * basevalue;
        double change = (g.genWienerproc(mu, timedelta, sigma));
        setChange(change);
        setMean(g.getDrift());
        return change;
    }
}
