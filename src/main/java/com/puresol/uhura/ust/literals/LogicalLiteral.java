package com.puresol.uhura.ust.literals;

import javax.i18n4java.Translator;

public class LogicalLiteral extends NumericalLiteral {

	private static final long serialVersionUID = -5622529938750219170L;

	private static final Translator translator = Translator
			.getTranslator(LogicalLiteral.class);

	private final boolean value;

	public LogicalLiteral(String originalSymbol, boolean value) {
		super(originalSymbol);
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

	@Override
	public String getName() {
		return translator.i18n("Logical Literal: {0}", value);
	}

}
