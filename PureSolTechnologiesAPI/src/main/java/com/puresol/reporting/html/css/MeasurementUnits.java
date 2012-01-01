package com.puresol.reporting.html.css;

public enum MeasurementUnits {
	POINT("pt"), PICA("pc"), INCH("in"), MILLIMETER("mm"), CENTIMETER("cm"), PIXEL(
			"px"), EM("em"), EX("ex"), PERCENT("%");

	private final String symbol;

	private MeasurementUnits(String symbol) {
		this.symbol = symbol;
	}

	public String toString() {
		return symbol;
	}
}
