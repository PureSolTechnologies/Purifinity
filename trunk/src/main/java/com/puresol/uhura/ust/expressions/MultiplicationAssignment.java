package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class MultiplicationAssignment extends AssignmentExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(MultiplicationAssignment.class);

	public MultiplicationAssignment(String originalSymbol, Expression target,
			Expression factor) {
		super(originalSymbol, target, factor);
	}

	@Override
	public String getName() {
		return translator.i18n("Multiplication Assignment");
	}

}
