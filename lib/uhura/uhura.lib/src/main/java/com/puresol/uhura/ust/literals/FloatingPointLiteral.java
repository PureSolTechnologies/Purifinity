package com.puresol.uhura.ust.literals;


public class FloatingPointLiteral extends NumericalLiteral {

    private static final long serialVersionUID = -5622529938750219170L;

    private final int bitLength;
    private final double value;

    public FloatingPointLiteral(String originalSymbol, int bitLength,
	    double value) {
	super(originalSymbol);
	this.bitLength = bitLength;
	this.value = value;
    }

    public int getBitLength() {
	return bitLength;
    }

    public double getValue() {
	return value;
    }

    @Override
    public String getName() {
	return bitLength + "bit Floating Point Literal: " + value;
    }

}
