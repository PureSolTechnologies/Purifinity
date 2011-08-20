package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class Exponent extends BinaryOperatorExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(Exponent.class);

	public Exponent(String originalSymbol, Expression mantissa,
			Expression exponent) {
		super(originalSymbol, mantissa, exponent);
	}

	@Override
	public String getName() {
		return translator.i18n("Exponent");
	}

}
