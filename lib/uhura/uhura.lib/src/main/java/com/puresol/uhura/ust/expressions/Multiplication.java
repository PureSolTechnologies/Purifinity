package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class Multiplication extends BinaryOperatorExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(Multiplication.class);

	public Multiplication(String originalSymbol, Expression factor1,
			Expression factor2) {
		super(originalSymbol, factor1, factor2);
	}

	@Override
	public String getName() {
		return translator.i18n("Multiplication");
	}

}