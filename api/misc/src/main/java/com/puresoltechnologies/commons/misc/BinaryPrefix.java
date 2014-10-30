package com.puresoltechnologies.commons.misc;

import java.math.BigInteger;

/**
 * This class represents a binary prefix used for file sizes, memory sizes and
 * so forth.
 * 
 * For details, have a look to: <a
 * href="http://en.wikipedia.org/wiki/Unit_prefix"
 * >http://en.wikipedia.org/wiki/Unit_prefix</a>
 * 
 * @author Rick-Rainer Ludwig
 */
public enum BinaryPrefix {

    ONE("", ""), KILO("k", "Kilo"), MEGA("M", "Mega"), GIGA("G", "Giga"), TERA(
	    "T", "Tera"), PETA("P", "Peta"), EXA("E", "Exa"), ZETTA("Z",
	    "Zetta"), YOTTA("Y", "Yotta");

    private final String unit;
    private final String name;
    private final BigInteger binaryFactor;
    private final BigInteger decimalFactor;

    private BinaryPrefix(String unit, String name) {
	this.unit = unit;
	this.name = name;

	int powerDividedByThree = ordinal(); // already divided by three!

	this.binaryFactor = BigInteger.valueOf(1024).pow(powerDividedByThree);
	this.decimalFactor = BigInteger.TEN.pow(powerDividedByThree * 3);
    }

    @Override
    public String toString() {
	return unit;
    }

    public String getUnit() {
	return unit;
    }

    public String getName() {
	return name;
    }

    public BigInteger getBinaryFactor() {
	return binaryFactor;
    }

    public BigInteger getDecimalFactor() {
	return decimalFactor;
    }

}
