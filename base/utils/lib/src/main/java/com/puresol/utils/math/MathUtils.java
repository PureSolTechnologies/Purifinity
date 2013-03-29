package com.puresol.utils.math;

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
     * Splits a long value into an array of long values which sum up to the
     * original value, but are splitted into amounts given by ratios.
     * 
     * @param amount
     *            is the total amount to be splitted.
     * @param ratios
     *            are the ratios for all shares.
     * @return An array of long is returned containing the splitted amounts.
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

}
