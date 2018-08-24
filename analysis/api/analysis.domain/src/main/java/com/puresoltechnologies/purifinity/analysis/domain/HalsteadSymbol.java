package com.puresoltechnologies.purifinity.analysis.domain;

public class HalsteadSymbol {

	private final boolean relevant;
	private final boolean operator;
	private final String symbol;

	public HalsteadSymbol(boolean relevant, boolean operator, String symbol) {
		super();
		this.relevant = relevant;
		this.operator = operator;
		this.symbol = symbol;
	}

	public boolean isRelevant() {
		return relevant;
	}

	public boolean isOperator() {
		return operator;
	}

	public String getSymbol() {
		return symbol;
	}

}
