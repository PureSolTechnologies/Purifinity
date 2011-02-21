package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class Assignment extends AssignmentExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(Assignment.class);

	public Assignment(String originalSymbol, Expression target, Expression value) {
		super(originalSymbol, target, value);
	}

	@Override
	public String getName() {
		return translator.i18n("Assignment");
	}

}
