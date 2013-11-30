package com.puresoltechnologies.commons.math;

public class CompoundSIUnitWithMoney extends CompoundSIUnit {

	private final String currency;
	private int currencyExponent = 0;

	public CompoundSIUnitWithMoney(String currency) {
		super();
		this.currency = currency;
	}

	public void setCurrencyExponent(int currencyExponent) {
		this.currencyExponent = currencyExponent;
	}

	@Override
	public String toString() {
		String string = super.toString();
		if (currencyExponent == 0) {
			return string;
		}
		if (currencyExponent > 0) {
			return currency + "^" + currencyExponent + " " + string;
		} else {
			return string.replaceFirst("/ \\(", "/ (" + currency + "^"
					+ currencyExponent + " ");
		}
	}

}
