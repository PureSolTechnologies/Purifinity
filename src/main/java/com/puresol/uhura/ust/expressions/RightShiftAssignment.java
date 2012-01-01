package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class RightShiftAssignment extends AssignmentExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(RightShiftAssignment.class);

	public RightShiftAssignment(String originalSymbol, Expression target,
			Expression shiftWidth) {
		super(originalSymbol, target, shiftWidth);
	}

	@Override
	public String getName() {
		return translator.i18n("Right Shift Assignment");
	}

}
