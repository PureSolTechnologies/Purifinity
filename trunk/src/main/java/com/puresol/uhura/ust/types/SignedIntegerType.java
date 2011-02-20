package com.puresol.uhura.ust.types;

import javax.i18n4java.Translator;

/**
 * This class represents a signed integer type with variable to set bit length.
 * 
 * @author ludwig
 * 
 */
public class SignedIntegerType extends IntegerType {

	private static final long serialVersionUID = 2322028164241606186L;

	private static final Translator translator = Translator
			.getTranslator(SignedIntegerType.class);

	public SignedIntegerType(String originalSymbol, int bitLength) {
		super(originalSymbol, bitLength);
	}

	@Override
	public String getName() {
		return translator.i18n("Signed {0} bit Integer Type", getBitLength());
	}

}
