package com.puresoltechnologies.commons.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * This class contains some helper functions which help to achieve some
 * mathematical goals.
 * 
 * The function herein a are unsorted and are not categorized.
 * 
 * @author Rick-Rainer Ludwig
 */
public class MathUtils {

	/**
	 * Private default constructor to avoid instantiation.
	 */
	private MathUtils() {
	}

	/**
	 * Splits a long value into an array of long values which sum up to the
	 * original value, but are splitted into amounts given by ratios.
	 * 
	 * @param amount
	 *            is the total amount to be split.
	 * @param ratios
	 *            are the ratios for all shares.
	 * @return An array of long is returned containing the split amounts.
	 */
	public static long[] allocate(long amount, double... ratios) {
		if ((ratios == null) || (ratios.length == 0)) {
			throw new IllegalArgumentException(
					"At least one ratio value needs to be provided.");
		}
		double total = 0;
		for (int i = 0; i < ratios.length; i++)
			total += ratios[i];
		if (total <= 0.0) {
			throw new IllegalArgumentException(
					"The sum of all ratios need to be greater than zero!");
		}
		long remainder = amount;
		long[] results = new long[ratios.length];
		for (int i = 0; i < results.length; i++) {
			results[i] = (long) Math.floor(amount * ratios[i] / total);
			remainder -= results[i];
		}

		for (int i = 0; i < remainder; i++) {
			results[i]++;
		}

		return results;
	}

	/**
	 * Splits a long value into an array of long values which sum up to the
	 * original value, but are splitted into amounts given by ratios.
	 * 
	 * @param amount
	 *            is the total amount to be splitted.
	 * @param ratios
	 *            are the ratios for all shares.
	 * @return An array of long is returned containing the splitted amounts.
	 */
	public static long[] allocate(long amount, int... ratios) {
		if ((ratios == null) || (ratios.length == 0)) {
			throw new IllegalArgumentException(
					"At least one ratio value needs to be provided.");
		}
		int total = 0;
		for (int i = 0; i < ratios.length; i++)
			total += ratios[i];
		if (total <= 0) {
			throw new IllegalArgumentException(
					"The sum of all ratios need to be greater than zero!");
		}
		long remainder = amount;
		long[] results = new long[ratios.length];
		for (int i = 0; i < results.length; i++) {
			results[i] = amount * ratios[i] / total;
			remainder -= results[i];
		}

		for (int i = 0; i < remainder; i++) {
			results[i]++;
		}

		return results;
	}

	/**
	 * This method is used for arbitrary accuracy rounding.
	 * 
	 * @param value
	 *            is the value to be rounded.
	 * @param accuracy
	 *            is the accuracy of the requested rounding. A value greater 0
	 *            is the number of digits after the decimal sign. A value less
	 *            than 0 rounds the number of digits before the decimal sign to
	 *            zero.
	 * @return The rounded value is returned.
	 */
	public static double round(double value, int accuracy) {
		double factor = Math.pow(10.0, accuracy);
		return Math.round(value * factor) / factor;
	}

	/**
	 * This method is used for arbitrary accuracy rounding.
	 * 
	 * @param value
	 *            is the value to be rounded.
	 * @param accuracy
	 *            is the accuracy of the requested rounding. A value greater 0
	 *            is the number of digits after the decimal sign. A value less
	 *            than 0 rounds the number of digits before the decimal sign to
	 *            zero.
	 * @return The rounded value is returned.
	 */
	public static BigDecimal round(BigDecimal value, int accuracy) {
		MathContext context = new MathContext(accuracy, RoundingMode.HALF_EVEN);
		BigDecimal factor = BigDecimal.valueOf(10.0).pow(accuracy);
		return value.multiply(factor).round(context).divide(factor);
	}

}
