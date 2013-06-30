package com.puresol.purifinity.uhura.ust.types;

/**
 * This class is a prototype for all kind of integer types (signed and
 * unsigned).
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class IntegerType extends NumericType {

	private static final long serialVersionUID = 1208013511094715942L;

	public IntegerType(String originalSymbol, int bitLength) {
		super(originalSymbol, bitLength);
	}

}
