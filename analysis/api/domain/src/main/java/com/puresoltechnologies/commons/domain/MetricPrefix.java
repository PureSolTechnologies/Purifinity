package com.puresoltechnologies.commons.domain;

import java.math.BigDecimal;

/**
 * This class represents a metric prefix used for physical values.
 * 
 * For details, have a look to: <a
 * href="http://en.wikipedia.org/wiki/Unit_prefix"
 * >http://en.wikipedia.org/wiki/Unit_prefix</a>
 * 
 * @author Rick-Rainer Ludwig
 */
public enum MetricPrefix {

    YOKTO(-24, "y", "Yokto"), ZEPTO(-21, "z", "Zepto"), ATTO(-18, "a", "Atto"), FEMTO(
	    -15, "f", "Femto"), PICO(-12, "p", "Pico"), NANO(-9, "n", "Nano"), MICRO(
	    -6, "u", "Micro"), MILLI(-3, "m", "Milli"), CENTI(-2, "c", "Centi"), DECI(
	    -1, "d", "Deci"), ONE(0, "", ""), DECA(1, "da", "Deca"), HECTO(2,
	    "h", "Hecto"), KILO(3, "k", "Kilo"), MEGA(6, "M", "Mega"), GIGA(9,
	    "G", "Giga"), TERA(12, "T", "Tera"), PETA(15, "P", "Peta"), EXA(18,
	    "E", "Exa"), ZETTA(21, "Z", "Zetta"), YOTTA(24, "Y", "Yotta");

    private final int decimalPower;
    private final String unit;
    private final String name;
    private final BigDecimal factor;

    private MetricPrefix(int decimalPower, String unit, String name) {
	this.decimalPower = decimalPower;
	this.unit = unit;
	this.name = name;

	if (decimalPower > 0) {
	    this.factor = BigDecimal.TEN.pow(decimalPower);
	} else if (decimalPower < 0) {
	    BigDecimal factor = BigDecimal.ONE;
	    for (int i = 0; i < -decimalPower; ++i) {
		factor = factor.divide(BigDecimal.TEN);
	    }
	    this.factor = factor;
	} else {
	    factor = BigDecimal.ONE;
	}
    }

    @Override
    public String toString() {
	return unit;
    }

    public int getDecimalPower() {
	return decimalPower;
    }

    public String getUnit() {
	return unit;
    }

    public String getName() {
	return name;
    }

    public BigDecimal getFactor() {
	return factor;
    }

}
