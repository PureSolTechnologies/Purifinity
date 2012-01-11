package com.puresol.uhura.ust.types;

import javax.i18n4java.Translator;

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

	private static final Translator translator = Translator
			.getTranslator(FloatingPointType.class);

	public FloatingPointType(String originalSymbol, int bitLength) {
		super(originalSymbol, bitLength);
	}

	@Override
	public String getName() {
		return translator.i18n("{0} bit Floating Point Type", getBitLength());
	}
}
