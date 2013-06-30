package com.puresol.purifinity.uhura.ust.types;

/**
 * This is an abstract numeric type which is a just prototype.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class NumericType extends Type {

	private static final long serialVersionUID = -348395824381107195L;

	private final int bitLength;

	public NumericType(String originalSymbol, int bitLength) {
		super(originalSymbol);
		this.bitLength = bitLength;
	}

	public final int getBitLength() {
		return bitLength;
	}

}
