package com.puresol.math.hp;

import java.math.BigDecimal;

/**
 * This class calculates the Gaussian Distribution for specified average and
 * standard deviation. The main information was take from:
 * http://en.wikipedia.org/wiki/Normal_distribution
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HPGaussianDistribution {

	private BigDecimal average = BigDecimal.valueOf(0.0);
	private BigDecimal standardDeviation = BigDecimal.valueOf(1.0);

	public HPGaussianDistribution() {
	}

	public HPGaussianDistribution(BigDecimal average, BigDecimal variance) {
		this.average = average;
		this.standardDeviation = variance;
	}

	public BigDecimal getAverage() {
		return average;
	}

	public void setAverage(BigDecimal average) {
		this.average = average;
	}

	public BigDecimal getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(BigDecimal deviation) {
		this.standardDeviation = deviation;
	}

	public BigDecimal function(BigDecimal x) {
		BigDecimal result = BigDecimal.valueOf(1.0).divide(
				HPMath.sqrt(BigDecimal.valueOf(2.0 * Math.PI)).multiply(
						standardDeviation));
		result.multiply(BigDecimal.valueOf(Math.exp(BigDecimal.valueOf(-0.5)
				.multiply(
						HPMath.sqr((x.subtract(average))
								.divide(standardDeviation))).doubleValue())));
		return result;
	}

//	public BigDecimal entropy() {
//		return Math.log(standardDeviation * Math.sqrt(2.0 * Math.PI * Math.E));
//	}

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
	// public BigDecimal simepleIntegration(BigDecimal left, BigDecimal right,
	// int intervals) {
	// BigDecimal leftEdge = average - 10.0 * standardDeviation;
	// BigDecimal rightEdge = average + 10.0 * standardDeviation;
	// BigDecimal intWidth = (rightEdge - leftEdge) / (BigDecimal) intervals;
	//
	// BigDecimal result = 0.0;
	// for (int interval = 0; interval < intervals; interval++) {
	// BigDecimal x = leftEdge + intWidth * (0.5 + (BigDecimal) interval);
	// BigDecimal y = Math.exp(-0.5
	// * sqr((x - average) / standardDeviation));
	// result += y * intWidth;
	// }
	// result /= (Math.sqrt(2.0 * Math.PI) * standardDeviation);
	// return result;
	// }

	public BigDecimal phi(BigDecimal x) {
		return BigDecimal.valueOf(0.5).multiply(
				BigDecimal.valueOf(1.0).add(
						HPErrorFunction.erf(x.divide(HPMath.sqrt(BigDecimal
								.valueOf(2.0))))));
	}

	/**
	 * This is a numerical calculation of the integral via error function erf.
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public BigDecimal integration(BigDecimal left, BigDecimal right) {
		return phi(right).subtract(phi(left));
	}

	public static BigDecimal confidence(BigDecimal n) {
		return HPErrorFunction.erf(n.divide(HPMath
				.sqrt(BigDecimal.valueOf(2.0))));
	}

	public static BigDecimal sigmaLevel(BigDecimal confidence) {
		return HPMath.sqrt(BigDecimal.valueOf(2.0)).multiply(
				HPErrorFunction.erfc(confidence));
	}
}
