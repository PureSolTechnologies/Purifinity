package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class Substraction extends BinaryOperatorExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(Substraction.class);

	public Substraction(String originalSymbol, Expression minutent,
			Expression subtrahent) {
		super(originalSymbol, minutent, subtrahent);
	}

	@Override
	public String getName() {
		return translator.i18n("Substraction");
	}

}
