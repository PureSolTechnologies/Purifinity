package com.puresol.coding.metrics.halstead;

public class HalsteadSymbol {

	private final boolean countable;
	private final boolean operator;
	private final String symbol;

	public HalsteadSymbol(boolean countable, boolean operator, String symbol) {
		super();
		this.countable = countable;
		this.operator = operator;
		this.symbol = symbol;
	}

	public boolean isCountable() {
		return countable;
	}

	public boolean isOperator() {
		return operator;
	}

	public String getSymbol() {
		return symbol;
	}

}
