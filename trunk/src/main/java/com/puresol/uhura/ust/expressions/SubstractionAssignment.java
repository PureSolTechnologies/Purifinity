package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class SubstractionAssignment extends AssignmentExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(SubstractionAssignment.class);

	public SubstractionAssignment(String originalSymbol, Expression target,
			Expression subtrahent) {
		super(originalSymbol, target, subtrahent);
	}

	@Override
	public String getName() {
		return translator.i18n("Substraction Assignment");
	}

}
