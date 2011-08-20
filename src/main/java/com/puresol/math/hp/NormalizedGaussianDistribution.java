package com.puresol.math.hp;

/**
 * This class calculates the normalized Gaussian Distribution (average=0.0,
 * sigma = 1.0). The main information was take from:
 * http://en.wikipedia.org/wiki/Normal_distribution
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NormalizedGaussianDistribution {

    public NormalizedGaussianDistribution() {
    }

    public double function(double x) {
	double result = 1.0 / (Math.sqrt(2.0 * Math.PI));
	result *= Math.exp(-0.5 * sqr(x));
	return result;
    }

    private double sqr(double d) {
	return d * d;
    }

    public double entropy() {
	return Math.log(Math.sqrt(2.0 * Math.PI * Math.E));
    }

    /**
     * This class integrates numericallty via center center point trapez
     * method. It's not very accurate!!!
     * 
     * @param left
     *            is the left integral edge.
     * @param right
     *            is the right integral edge.
     * @param intervals
     *            is the number of intervals to be used.
     * @return The integrated value is returned.
     */
    public double simpleIntegration(double left, double right,
	    int intervals) {
	double intWidth = (right - left) / (double) intervals;

	double result = 0.0;
	for (int interval = 0; interval < intervals; interval++) {
	    double x = left + intWidth * (0.5 + (double) interval);
	    double y = Math.exp(-0.5 * sqr(x));
	    result += y * intWidth;
	}
	result /= (Math.sqrt(2.0 * Math.PI));
	return result;
    }

    public double phi(double x) {
	return 0.5 * (1.0 + ErrorFunction.erf(x / Math.sqrt(2.0)));
    }

    /**
     * This is a numerical calculation of the integral via error function
     * erf.
     * 
     * @param left
     * @param right
     * @return
     */
    public double integration(double left, double right) {
	return phi(right) - phi(left);
    }

    public static double confidence(double n) {
	return ErrorFunction.erf(n / Math.sqrt(2.0));
    }

    public static double sigmaLevel(double confidence) {
	return Math.sqrt(2.0) * ErrorFunction.erfc(confidence);
    }
}
