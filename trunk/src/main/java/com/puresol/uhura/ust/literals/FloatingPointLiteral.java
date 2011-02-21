package com.puresol.uhura.ust.literals;

import javax.i18n4java.Translator;

public class FloatingPointLiteral extends NumericalLiteral {

	private static final long serialVersionUID = -5622529938750219170L;

	private static final Translator translator = Translator
			.getTranslator(FloatingPointLiteral.class);

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
		return translator.i18n("{0}bit Floating Point Literal: {1}", bitLength,
				value);
	}

}
