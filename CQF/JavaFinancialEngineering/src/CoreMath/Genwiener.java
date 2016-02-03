package CoreMath;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:42:28
 * To change this template use File | Settings | File Templates.
 */
public class Genwiener {

    /**
     * Creates a new instance of Genwiener
     */
    public Genwiener() {
    }

    private double constdrift;
    private double wienervalue;

    private void setDrift(double driftval) {
        constdrift = driftval;
    }

    public double getDrift() {
        return constdrift;
    }

    private void setWiener(double wienval) {
        wienervalue = wienval;
    }

    public double getwienerVal() {
        return wienervalue;
    }

    public double genWienerproc(double drift, double t, double sd) {
        Wiener w = new Wiener();
        double deltaz;
        double driftvalue;
        double deltax;
        deltaz = w.wienerProc(t);
        setWiener(deltaz);
        driftvalue = drift * t;
        setDrift(driftvalue);

        deltax = (driftvalue + (sd * deltaz));
        return deltax;
    }
}

