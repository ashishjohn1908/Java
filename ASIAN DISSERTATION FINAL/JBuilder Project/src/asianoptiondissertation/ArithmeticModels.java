package asianoptiondissertation;

/**
 * <p>Title: MSc Financial Market with Information Systems</p>
 *
 * <p>Description: Asian Option Pricer</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Orajava Consultancy Ltd</p>
 *
 * @author Plamen Stilyianov
 * @version 1.0
 */
public class ArithmeticModels {

    //greeks
    public double delta, gamma, vega, rho, theta = 0.0;
    public double valueLevy, valueTurnbullWakeman = 0.0;

    public ArithmeticModels() {
    }

    public ArithmeticModels(double S, double K, double r, double div, double sig, double T) {

        valueTurnbullWakeman = modelTurnbullWakemanAsian(S, K, r, div, sig, T);
        valueLevy = modelLevyAsian(S, K, r, div, sig, T);
        delta = deltaArithmetic(S, K, T, r, div, sig);
        gamma = gammaArithmetic(S, K, T, r, div, sig);
        theta = thetaArithmetic(S, K, T, r, div, sig) / 365;
        vega = vegaArithmetic(S, K, T, r, div, sig) / 100;
        rho = rhoArithmetic(S, K, T, r, div, sig) / 100;
    }

    private double modelTurnbullWakemanAsian(double S, double K, double r, double div, double sig, double T) {

        //constant
        double b = r - div;   // cost of carry rate
        double tau = 0.0;   // time to the begining of the average period

        double m1 = 0.0;
        double m2 = 0.0;

        double optionValue = 0.0;
        double x = 0.0;
        m1 = ((Math.exp(b * T) - Math.exp(b * tau)) / (b * (T - tau))) * S;
        m2 = Math.pow(S, 2) * 2 * Math.exp((2 * b + Math.pow(sig, 2)) * T) / ((b + Math.pow(sig, 2)) * (2 * b + Math.pow(sig, 2)) * Math.pow(T - tau, 2))
                + Math.pow(S, 2) * 2 * Math.exp((2 * b + Math.pow(sig, 2)) * tau) / (b * Math.pow(T - tau, 2)) * (1 / (2 * b + Math.pow(sig, 2)) - (Math.exp(b * (T - tau))) / (b + Math.pow(sig, 2)));

        double modVol = Math.sqrt((1 / T) * Math.log(m2 / Math.pow(m1, 2))) * 100;

        optionValue = modelBlack76(K, m1, modVol, T, r);
        return optionValue;
    }

    private double modelBlack76(double strike, double m1, double modVol, double yrs, double r) {
        double optionPrice = 0.0;
        double d1 = 0.0;
        double d2 = 0.0;
        double nd1 = 0.0;
        double nd2 = 0.0;

        d1 = (Math.log(m1 / strike) + Math.pow((modVol / 100), 2) * (yrs / 2)) / ((modVol / 100) * Math.sqrt(yrs));
        d2 = d1 - (modVol / 100) * Math.sqrt(yrs);

        nd1 = CND(d1);
        nd2 = CND(d2);

        optionPrice = Math.exp(-r * yrs) * (m1 * nd1 - strike * nd2);
        return optionPrice;
    }

    private double modelLevyAsian(double S, double K, double r, double div, double sig, double T) {
        double valueLevy = 0.0;
        double se = 0.0;
        double m = 0.0;
        double d = 0.0;
        double sv = 0.0;
        double xStar = 0.0;
        double d1, d2 = 0.0;
        double b = r - div;   // cost of carry rate
        double t2 = T;   // remaining time to maturity
        double sa = S; // Arithmetic Average price

        se = S / (T * b) * (Math.exp((b - r) * t2) - Math.exp(-r * t2));

        m = 2 * Math.pow(S, 2) / (b + Math.pow(sig, 2)) * ((Math.exp((2 * b + Math.pow(sig, 2)) * t2) - 1) / (2 * b + Math.pow(sig, 2)) - (Math.exp(b * t2) - 1) / b);
        d = m / Math.pow(T, 2);
        sv = Math.log(d) - 2 * (r * t2 + Math.log(se));
        xStar = K - ((T - t2) / T) * sa;
        d1 = 1 / Math.sqrt(sv) * (Math.log(d) / 2 - Math.log(xStar));
        d2 = d1 - Math.sqrt(sv);

        valueLevy = se * CND(d1) - xStar * Math.exp(-r * t2) * CND(d2);
        return valueLevy;
    }

    private double CND(double X) {
        double L, K, w;
        double a1 = 0.31938153, a2 = -0.356563782, a3 = 1.781477937, a4 = -1.821255978, a5 = 1.330274429;

        L = Math.abs(X);
        K = 1.0 / (1.0 + 0.2316419 * L);
        w = 1.0 - 1.0 / Math.sqrt(2.0 * Math.PI) * Math.exp(-L * L / 2) * (a1 * K + a2 * K * K + a3 * Math.pow(K, 3) + a4 * Math.pow(K, 4) + a5 * Math.pow(K, 5));

        if (X < 0.0) {
            w = 1.0 - w;
        }
        return w;
    }

    private double ND(double X) {
        double w;

        w = Math.exp(Math.pow(-X, 2) / 2) / Math.sqrt(2 * Math.PI);

        return w;
    }

    // GREEKS


    private double deltaArithmetic(double S, double K, double T, double r, double div, double sig) {
        double deltaAr = 0.0;
        double m1 = 0.0;
        double m2 = 0.0;
        double ba = 0.0;
        double b = r - div;
        double tau = 0.0;
        double va = 0.0;
        double d1 = 0.0;

        m1 = (Math.exp(b * T) - Math.exp(b * tau)) / (b * (T - tau));

        m2 = 2 * Math.exp((2 * b + Math.pow(sig, 2)) * T) / ((b + Math.pow(sig, 2)) * (2 * b + Math.pow(sig, 2)) * Math.pow((T - tau), 2)) + 2 * Math.exp((2 * b + Math.pow(sig, 2)) * tau) / (b * Math.pow((T - tau), 2)) * (1 / (2 * b + Math.pow(sig, 2)) - Math.exp(b * (T - tau)) / (b + Math.pow(sig, 2)));

        ba = Math.log(m1) / T;

        va = Math.sqrt(Math.log(m2) / T - 2 * ba);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));

        deltaAr = Math.exp((ba - r) * T) * CND(d1);

        return deltaAr;
    }

    private double gammaArithmetic(double S, double K, double T, double r, double div, double sig) {
        double gammaAr = 0.0;
        double m1 = 0.0;
        double m2 = 0.0;
        double ba = 0.0;
        double b = r - div;
        double tau = 0.0;
        double va = 0.0;
        double d1 = 0.0;

        m1 = (Math.exp(b * T) - Math.exp(b * tau)) / (b * (T - tau));

        m2 = 2 * Math.exp((2 * b + Math.pow(sig, 2)) * T) / ((b + Math.pow(sig, 2)) * (2 * b + Math.pow(sig, 2)) * Math.pow((T - tau), 2)) + 2 * Math.exp((2 * b + Math.pow(sig, 2)) * tau) / (b * Math.pow((T - tau), 2)) * (1 / (2 * b + Math.pow(sig, 2)) - Math.exp(b * (T - tau)) / (b + Math.pow(sig, 2)));

        ba = Math.log(m1) / T;

        va = Math.sqrt(Math.log(m2) / T - 2 * ba);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));

        gammaAr = Math.exp((ba - r) * T) * CND(d1) / (S * va * Math.sqrt(T));

        return gammaAr;
    }

    private double thetaArithmetic(double S, double K, double T, double r, double div, double sig) {
        double thetaAr = 0.0;
        double m1 = 0.0;
        double m2 = 0.0;
        double ba = 0.0;
        double b = r - div;
        double tau = 0.0;
        double va = 0.0;
        double d1, d2 = 0.0;

        m1 = (Math.exp(b * T) - Math.exp(b * tau)) / (b * (T - tau));

        m2 = 2 * Math.exp((2 * b + Math.pow(sig, 2)) * T) / ((b + Math.pow(sig, 2)) * (2 * b + Math.pow(sig, 2)) * Math.pow((T - tau), 2)) + 2 * Math.exp((2 * b + Math.pow(sig, 2)) * tau) / (b * Math.pow((T - tau), 2)) * (1 / (2 * b + Math.pow(sig, 2)) - Math.exp(b * (T - tau)) / (b + Math.pow(sig, 2)));

        ba = Math.log(m1) / T;

        va = Math.sqrt(Math.log(m2) / T - 2 * ba);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));
        d2 = d1 - (sig * Math.sqrt(T));

        thetaAr = -(S * Math.exp((ba - r) * T) * ND(d1) * va) / (2 * Math.sqrt(T)) - (r * K * Math.exp(-r * T) * CND(d2)) - ((ba - r) * S * CND(d1) * Math.exp((ba - r) * T));
        return thetaAr;
    }

    private double vegaArithmetic(double S, double K, double T, double r, double div, double sig) {
        double vegaAr = 0.0;
        double m1 = 0.0;
        double m2 = 0.0;
        double ba = 0.0;
        double b = r - div;
        double tau = 0.0;
        double va = 0.0;
        double d1, d2 = 0.0;

        m1 = (Math.exp(b * T) - Math.exp(b * tau)) / (b * (T - tau));

        m2 = 2 * Math.exp((2 * b + Math.pow(sig, 2)) * T) / ((b + Math.pow(sig, 2)) * (2 * b + Math.pow(sig, 2)) * Math.pow((T - tau), 2)) + 2 * Math.exp((2 * b + Math.pow(sig, 2)) * tau) / (b * Math.pow((T - tau), 2)) * (1 / (2 * b + Math.pow(sig, 2)) - Math.exp(b * (T - tau)) / (b + Math.pow(sig, 2)));

        ba = Math.log(m1) / T;
        va = Math.sqrt(Math.log(m2) / T - 2 * ba);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));
        // d2 = d1 - (sig*Math.sqrt(T));

        vegaAr = S * Math.exp((ba - r) * T) * ND(d1) * Math.sqrt(T);
        return vegaAr;
    }

    private double rhoArithmetic(double S, double K, double T, double r, double div, double sig) {
        double rhoAr = 0.0;
        double m1 = 0.0;
        double m2 = 0.0;
        double ba = 0.0;
        double b = r - div;
        double tau = 0.0;
        double va = 0.0;
        double d1, d2 = 0.0;

        m1 = (Math.exp(b * T) - Math.exp(b * tau)) / (b * (T - tau));

        m2 = 2 * Math.exp((2 * b + Math.pow(sig, 2)) * T) / ((b + Math.pow(sig, 2)) * (2 * b + Math.pow(sig, 2)) * Math.pow((T - tau), 2)) + 2 * Math.exp((2 * b + Math.pow(sig, 2)) * tau) / (b * Math.pow((T - tau), 2)) * (1 / (2 * b + Math.pow(sig, 2)) - Math.exp(b * (T - tau)) / (b + Math.pow(sig, 2)));

        ba = Math.log(m1) / T;
        va = Math.sqrt(Math.log(m2) / T - 2 * ba);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));
        d2 = d1 - (sig * Math.sqrt(T));

        if (b != 0)
            rhoAr = T * K * Math.exp(-r * T) * CND(d2);
        else {
            rhoAr = -T * new GenBlackScholesModels(S, K, r, div, sig, T).valueBlackScholes;
        }


        return rhoAr;
    }

    public static void main(String [] args) {

        //initial parameters
        // double S = 4493;    // Stock price
        // double K = 4493;    // strike price
        // double T = 0.24658;      // Maturity date
        // double sig = 0.21097;  // Volatility
        // double r = 0.04314;   // Interest rate
        // double div = 0.0087; // Divident yield

        double S = 35.75;    // Stock price
        double K = 35.75;    // strike price
        double T = 0.24658;      // Maturity date
        double sig = 0.26354;  // Volatility
        double r = 0.04271;   // Interest rate
        double div = 0.0457; // Divident yield

        ArithmeticModels arTest = new ArithmeticModels(S, K, r, div, sig, T);

        System.out.println("turnbullWakeman: " + arTest.valueTurnbullWakeman);
        System.out.println("levy: " + arTest.valueLevy);
        System.out.println("delta: " + arTest.delta);
        System.out.println("gamma: " + arTest.gamma);
        System.out.println("theta: " + arTest.theta);
        System.out.println("vega: " + arTest.vega);
        System.out.println("rho: " + arTest.rho);
    }
}
