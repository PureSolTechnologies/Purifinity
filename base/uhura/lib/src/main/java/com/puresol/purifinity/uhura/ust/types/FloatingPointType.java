package com.puresol.purifinity.uhura.ust.types;

/**
 * This class represents a floating point type. Floating point types have
 * different names and bit length in different languages:
 * 
 * <pre>
 * 	float (32 bit), double (64 bit) - C / C++, Java
 *  real (32 bit), double precision (64 bit) - Fortran
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FloatingPointType extends NumericType {

    private static final long serialVersionUID = 47713101164319131L;

    public FloatingPointType(String originalSymbol, int bitLength) {
	super(originalSymbol, bitLength);
    }

    @Override
    public String getName() {
	return getBitLength() + "bit Floating Point Type";
    }
}
