package com.puresol.data;


/**
 * This class represents a simple fraction.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Fraction implements Comparable<Fraction>, Cloneable {

    /**
     * This is the numerator of the fraction.
     */
    protected int numerator;
    /**
     * This is the denominator of the fraction.
     */
    protected int denominator;

    /**
     * This is the standard constructor. The fraction is set to 0/1.
     */
    public Fraction() {
	numerator = 0;
	denominator = 1;
    }

    /**
     * This is the constructor for initial values.
     * 
     * @param numerator
     *            of the fraction.
     * @param denominator
     *            of the fraction.
     */
    public Fraction(int numerator, int denominator) {
	this();
	set(numerator, denominator);
    }

    /**
     * This method sets numerator and denominator of the fraction.
     * 
     * @param numerator
     *            of the fraction.
     * @param denominator
     *            of the fraction.
     */
    public void set(int numerator, int denominator)
	    throws IllegalArgumentException {
	if (denominator == 0) {
	    throw new IllegalArgumentException(
		    "Denominator is zero in Fraction!");
	}
	this.numerator = numerator;
	this.denominator = denominator;
    }

    /**
     * This method sets numerator and denominator of the fraction.
     * 
     * @param fraction
     *            is the Fraction to take the values out off.
     */
    public void set(Fraction fraction) {
	set(fraction.getNumerator(), fraction.getDenominator());
    }

    /**
     * This function returns the numerator of the fraction.
     * 
     * @return The numerator is returned.
     */
    public int getNumerator() {
	return numerator;
    }

    /**
     * This function returns the denominator of the fraction.
     * 
     * @return The denominator is returned.
     */
    public int getDenominator() {
	return denominator;
    }

    /**
     * This method converts the fraction into a double.
     * 
     * @return A double is return representing the fraction.
     */
    public double toDouble() {
	return (double) numerator / (double) denominator;
    }

    /**
     * This is the method for the Comparable interface.
     * 
     * @param fraction
     *            is the Fraction to compare to.
     * @return -1, 0 or 1 is returned if fraction is smaller than, equal or
     *         greater than this class.
     */
    @Override
    public int compareTo(Fraction fraction) {
	if (equals(fraction)) {
	    return 0;
	}
	if (fraction.toDouble() < toDouble()) {
	    return 1;
	}
	if (fraction.toDouble() > toDouble()) {
	    return -1;
	}
	return 0;
    }

    /**
     * This method overrides Object.equlas.
     * 
     * @param fraction
     *            is a Fraction to compare with.
     * @return True is returned if fraction equals the current values of this
     *         class.
     */
    @Override
    public boolean equals(Object aThat) {
	if (this == aThat)
	    return true;
	if (!(aThat instanceof Fraction))
	    return false;

	Fraction that = (Fraction) aThat;
	if (this.getNumerator() != that.getNumerator()) {
	    return false;
	}
	if (this.getDenominator() != that.getDenominator()) {
	    return false;
	}
	return true;
    }

    /**
     * This method overrides Object.hashCode.
     * 
     * @return A hash int is returned.
     */
    @Override
    public int hashCode() {
	return getNumerator() + getDenominator();
    }

    /**
     * This method converts the fraction into a string representation.
     * 
     * @return A String is returned containing the String representation.
     */
    @Override
    public String toString() {
	String str = String.valueOf(numerator);
	if (denominator != 1) {
	    str += "/" + denominator;
	}
	return str;
    }

    /**
     * This method converts the fraction into a string representation where
     * both, numerator and denominator, are set to absolute values.
     * 
     * @return A String is returned containing the String representation.
     */
    public String toAbsString() {
	String str = String.valueOf(Math.abs(numerator));
	if (denominator != 1) {
	    str += "/" + Math.abs(denominator);
	}
	return str;
    }

    @Override
    public Fraction clone() {
	Fraction cloned = null;
	try {
	    cloned = (Fraction) super.clone();
	    cloned.set(this);
	} catch (CloneNotSupportedException e) {
	    throw new RuntimeException(e);
	}
	return cloned;
    }

    public void add(Fraction fraction) {
	numerator *= fraction.denominator;
	numerator += denominator * fraction.numerator;
	denominator *= fraction.denominator;
    }

    public void sub(Fraction fraction) {
	numerator *= fraction.denominator;
	numerator -= denominator * fraction.numerator;
	denominator *= fraction.denominator;
    }

    public void multiply(Fraction fraction) {
	numerator *= fraction.numerator;
	denominator *= fraction.denominator;
    }

    public void divide(Fraction fraction) {
	numerator *= fraction.denominator;
	denominator *= fraction.numerator;
    }
}
