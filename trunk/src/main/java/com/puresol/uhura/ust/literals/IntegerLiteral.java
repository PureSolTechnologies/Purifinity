package com.puresol.uhura.ust.literals;

import javax.i18n4java.Translator;

public class IntegerLiteral extends NumericalLiteral {

	private static final long serialVersionUID = -5622529938750219170L;

	private static final Translator translator = Translator
			.getTranslator(IntegerLiteral.class);

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
		return translator.i18n("{0}bit Integer Literal: {1}", bitLength, value);
	}

}
