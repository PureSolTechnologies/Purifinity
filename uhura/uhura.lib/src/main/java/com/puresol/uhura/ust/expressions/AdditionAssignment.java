package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class AdditionAssignment extends AssignmentExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(AdditionAssignment.class);

	public AdditionAssignment(String originalSymbol, Expression target,
			Expression summand) {
		super(originalSymbol, target, summand);
	}

	@Override
	public String getName() {
		return translator.i18n("Addition Assignment");
	}

}
