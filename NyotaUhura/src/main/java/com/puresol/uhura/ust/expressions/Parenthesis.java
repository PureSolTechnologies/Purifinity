package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class Parenthesis extends UnaryOperatorExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(Parenthesis.class);

	public Parenthesis(String originalSymbol, Expression operand) {
		super(originalSymbol, operand);
	}

	@Override
	public String getName() {
		return translator.i18n("Parenthesis");
	}

}
