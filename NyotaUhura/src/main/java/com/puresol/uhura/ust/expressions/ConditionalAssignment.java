package com.puresol.uhura.ust.expressions;

import javax.i18n4java.Translator;

public class ConditionalAssignment extends TertiaryOperatorExpression {

	private static final long serialVersionUID = 6754414167669177011L;

	private static final Translator translator = Translator
			.getTranslator(ConditionalAssignment.class);

	public ConditionalAssignment(String originalSymbol, Expression target,
			Expression trueAlternative, Expression falseAlternative) {
		super(originalSymbol, target, trueAlternative, falseAlternative);
	}

	@Override
	public String getName() {
		return translator.i18n("Conditional Assignment");
	}

}
