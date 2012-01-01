package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class Addition extends BinaryOperatorExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(Addition.class);

	public Addition(String originalSymbol, Expression summand1,
			Expression summand2) {
		super(originalSymbol, summand1, summand2);
	}

	@Override
	public String getName() {
		return translator.i18n("Addition");
	}

}
