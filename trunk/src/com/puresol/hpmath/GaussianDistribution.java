package com.puresol.hpmath;

/**
 * This class calculates the Gaussian Distribution for specified average and
 * standard deviation. The main information was take from:
 * http://en.wikipedia.org/wiki/Normal_distribution
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GaussianDistribution {

	private double average = 0.0;
	private double standardDeviation = 1.0;

	/**
	 * This constructor leads to a normalized Gaussian Distribution.
	 */
	public GaussianDistribution() {
	}

	public GaussianDistribution(double average, double variance) {
		this.average = average;
		this.standardDeviation = variance;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(double deviation) {
		this.standardDeviation = deviation;
	}

	public double function(double x) {
		double result = 1.0 / (Math.sqrt(2.0 * Math.PI) * standardDeviation);
		result *= Math.exp(-0.5 * sqr((x - average) / standardDeviation));
		return result;
	}

	private double sqr(double d) {
		return d * d;
	}

	public double entropy() {
		return Math.log(standardDeviation * Math.sqrt(2.0 * Math.PI * Math.E));
	}

	/**
	 * This class integrates numericallty via center center point trapez method.
	 * It's not very accurate!!!
	 * 
	 * @param left
	 *            is the left integral edge.
	 * @param right
	 *            is the right integral edge.
	 * @param intervals
	 *            is the number of intervals to be used.
	 * @return The integrated value is returned.
	 */
	public double simpleIntegration(double left, double right, int intervals) {
		double intWidth = (right - left) / (double) intervals;

		double result = 0.0;
		for (int interval = 0; interval < intervals; interval++) {
			double x1 = left + intWidth * (double) interval;
			double y1 = Math
					.exp(-0.5 * sqr((x1 - average) / standardDeviation));
			double x2 = x1 + intWidth;
			double y2 = Math
					.exp(-0.5 * sqr((x2 - average) / standardDeviation));
			result += (y1 + y2) / 2.0 * intWidth;
		}
		result /= (Math.sqrt(2.0 * Math.PI) * standardDeviation);
		return result;
	}

	public double phi(double x) {
		return 0.5 * (1.0 + ErrorFunction.erf(x / Math.sqrt(2.0), average,
				standardDeviation));
	}

	/**
	 * This is a numerical calculation of the integral via error function erf.
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public double integration(double left, double right) {
		return phi(right) - phi(left);
	}

}
