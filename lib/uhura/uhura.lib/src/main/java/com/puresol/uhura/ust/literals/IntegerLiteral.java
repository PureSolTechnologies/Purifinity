package com.puresol.uhura.ust.literals;

public class IntegerLiteral extends NumericalLiteral {

    private static final long serialVersionUID = -5622529938750219170L;

    private final int bitLength;
    private final long value;

    public IntegerLiteral(String originalSymbol, int bitLength, long value) {
	super(originalSymbol);
	this.bitLength = bitLength;
	this.value = value;
    }

    public int getBitLength() {
	return bitLength;
    }

    public long getValue() {
	return value;
    }

    @Override
    public String getName() {
	return bitLength + "bit Integer Literal: " + value;
    }

}
