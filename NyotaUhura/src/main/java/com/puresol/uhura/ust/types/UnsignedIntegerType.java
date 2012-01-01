package com.puresol.uhura.ust.types;

import javax.i18n4java.Translator;

/**
 * This class represents an unsigned integer type.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UnsignedIntegerType extends IntegerType {

	private static final long serialVersionUID = 6211356961166357559L;

	private static final Translator translator = Translator
			.getTranslator(UnsignedIntegerType.class);

	public UnsignedIntegerType(String originalSymbol, int bitLength) {
		super(originalSymbol, bitLength);
	}

	@Override
	public String getName() {
		return translator.i18n("Signed {0} bit Integer Type", getBitLength());
	}

}
