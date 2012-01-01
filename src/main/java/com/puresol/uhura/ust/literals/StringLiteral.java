package com.puresol.uhura.ust.literals;

import javax.i18n4java.Translator;

public class StringLiteral extends NumericalLiteral {

	private static final long serialVersionUID = -5622529938750219170L;

	private static final Translator translator = Translator
			.getTranslator(StringLiteral.class);

	private final String value;

	public StringLiteral(String originalSymbol, String value) {
		super(originalSymbol);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String getName() {
		return translator.i18n("String Literal: {0}", value);
	}

}
