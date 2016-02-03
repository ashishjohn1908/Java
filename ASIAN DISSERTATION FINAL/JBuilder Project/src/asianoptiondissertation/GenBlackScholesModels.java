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
public class GenBlackScholesModels {

    //greeks
    public double delta, gamma, vega, rho, theta = 0.0;
    public double valueBlackScholes = 0.0;

    private GenBlackScholesModels() {
    }

    public GenBlackScholesModels(double S, double K, double r, double div, double sig, double T) {
        valueBlackScholes = modelGenBlackScholesAsian(S, K, r, div, sig, T);
        delta = deltaGeBlackScholes(S, K, T, r, div, sig);
        gamma = gammaGeBlackScholes(S, K, T, r, div, sig);
        theta = thetaGeBlackScholes(S, K, T, r, div, sig) / 365;
        vega = vegaGeBlackScholes(S, K, T, r, div, sig) / 100;
        rho = rhoGeBlackScholes(S, K, T, r, div, sig) / 100;
    }

    private double modelGenBlackScholesAsian(double S, double K, double r, double div, double sig, double T) {
        double valueBS = 0.0;
        double d1, d2 = 0.0;
        double b = r - div;

        d1 = (Math.log(S / K) + (b + 0.5 * Math.pow(sig, 2)) * T) / (sig * Math.sqrt(T));
        d2 = d1 - (sig * Math.sqrt(T));

        valueBS = (S * Math.exp((b - r) * T) * CND(d1)) - (K * Math.exp(-r * T) * CND(d2));

        return valueBS;
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

    private double deltaGeBlackScholes(double S, double K, double T, double r, double div, double sig) {
        double deltaGeBS = 0.0;
        double d1 = 0.0;
        double b = r - div;

        d1 = (Math.log(S / K) + (b + Math.pow(sig, 2) / 2) * T) / (sig * Math.sqrt(T));

        deltaGeBS = Math.exp((b - r) * T) * CND(d1);

        return deltaGeBS;
    }

    private double gammaGeBlackScholes(double S, double K, double T, double r, double div, double sig) {
        double gammaGeBS = 0.0;
        double d1 = 0.0;
        double b = r - div;

        d1 = (Math.log(S / K) + (b + Math.pow(sig, 2) / 2) * T) / (sig * Math.sqrt(T));

        gammaGeBS = Math.exp((b - r) * T) * CND(d1) / (S * sig * Math.sqrt(T));

        return gammaGeBS;
    }

    private double thetaGeBlackScholes(double S, double K, double T, double r, double div, double sig) {
        double thetaGeBS = 0.0;
        double d1, d2 = 0.0;
        double b = r - div;

        d1 = (Math.log(S / K) + (b + Math.pow(sig, 2) / 2) * T) / (sig * Math.sqrt(T));
        d2 = d1 - (sig * Math.sqrt(T));

        thetaGeBS = -(S * Math.exp((b - r) * T) * ND(d1) * sig) / (2 * Math.sqrt(T)) - (r * K * Math.exp(-r * T) * CND(d2)) - ((b - r) * S * CND(d1) * Math.exp((b - r) * T));
        //thetaGeBS = -(sig*S*ND(d1)*Math.exp(-div*T)) / (2*Math.sqrt(T)) - (r*K*CND(d2)*Math.exp(-r*T)) + (div*S*CND(d1)*Math.exp(-div*T));

        return thetaGeBS;
    }

    private double vegaGeBlackScholes(double S, double K, double T, double r, double div, double sig) {
        double vegaGr = 0.0;
        double d1 = 0.0;
        double b = r - div;

        d1 = (Math.log(S / K) + (b + Math.pow(sig, 2) / 2) * T) / (sig * Math.sqrt(T));

        vegaGr = S * Math.exp((b - r) * T) * ND(d1) * Math.sqrt(T);

        return vegaGr;
    }

    private double rhoGeBlackScholes(double S, double K, double T, double r, double div, double sig) {
        double rhoGeBS = 0.0;
        double d1, d2 = 0.0;
        double b = r - div;

        d1 = (Math.log(S / K) + (b + Math.pow(sig, 2) / 2) * T) / (sig * Math.sqrt(T));
        d2 = d1 - (sig * Math.sqrt(T));

        if (b != 0)
            rhoGeBS = T * K * Math.exp(-r * T) * CND(d2);
        else
            rhoGeBS = -T * modelGenBlackScholesAsian(S, K, r, div, sig, T);
        return rhoGeBS;
    }

    public static void main(String [] args) {

        //initial parameters
        // double S = 4493;    // Stock price
        // double K = 4493;    // strike price
        // double T = 0.24658;      // Maturity date
        // double sig = 0.21097;  // Volatility
        // double r = 0.04314;   // Interest rate
        //  double div = 0.0087; // Divident yield

        double S = 35.75;    // Stock price
        double K = 35.75;    // strike price
        double T = 0.24658;      // Maturity date
        double sig = 0.26354;  // Volatility
        double r = 0.04271;   // Interest rate
        double div = 0.0457; // Divident yield

        GenBlackScholesModels bsTest = new GenBlackScholesModels(S, K, r, div, sig, T);

        System.out.println("blackScholesOptionValue: " + bsTest.valueBlackScholes);
        System.out.println("Black Scholes delta: " + bsTest.delta);
        System.out.println("Black Scholes gamma: " + bsTest.gamma);
        System.out.println("Black Scholes theta: " + bsTest.theta);
        System.out.println("Black Scholes vega: " + bsTest.vega);
        System.out.println("Black Scholes rho: " + bsTest.rho);
    }
}
