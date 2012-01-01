package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class Division extends BinaryOperatorExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(Division.class);

	public Division(String originalSymbol, Expression divident,
			Expression divisor) {
		super(originalSymbol, divident, divisor);
	}

	@Override
	public String getName() {
		return translator.i18n("Division");
	}

}
