package com.puresol.purifinity.uhura.ust.types;

/**
 * This class represents an unsigned integer type.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UnsignedIntegerType extends AbstractIntegerType {

	private static final long serialVersionUID = 6211356961166357559L;

	public UnsignedIntegerType(String originalSymbol, int bitLength) {
		super("Signed " + bitLength + "bit Integer Type", originalSymbol,
				bitLength);
	}
}
