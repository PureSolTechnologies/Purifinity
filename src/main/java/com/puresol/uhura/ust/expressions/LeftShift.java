package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class LeftShift extends BinaryOperatorExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	private static final Translator translator = Translator
			.getTranslator(LeftShift.class);

	public LeftShift(String originalSymbol, Expression operand,
			Expression shiftWidth) {
		super(originalSymbol, operand, shiftWidth);
	}

	@Override
	public String getName() {
		return translator.i18n("Left Shift");
	}

}
