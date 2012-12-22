package com.puresol.uhura.ust.literals;


public class StringLiteral extends NumericalLiteral {

    private static final long serialVersionUID = -5622529938750219170L;

    private final String value;

    public StringLiteral(String originalSymbol, String value) {
	super(originalSymbol);
	this.value = value;
    }

    public String getValue() {
	return value;
    }

    @Override
    public String getName() {
	return "String Literal: " + value;
    }

}