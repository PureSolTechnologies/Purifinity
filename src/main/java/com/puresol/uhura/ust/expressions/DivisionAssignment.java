package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class DivisionAssignment extends AssignmentExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(DivisionAssignment.class);

	public DivisionAssignment(String originalSymbol, Expression target,
			Expression divisor) {
		super(originalSymbol, target, divisor);
	}

	@Override
	public String getName() {
		return translator.i18n("Division Assignment");
	}

}
