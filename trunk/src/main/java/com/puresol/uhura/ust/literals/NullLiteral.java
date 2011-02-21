package com.puresol.uhura.ust.literals;

import javax.i18n4java.Translator;

public class NullLiteral extends NumericalLiteral {

	private static final long serialVersionUID = -5622529938750219170L;

	private static final Translator translator = Translator
			.getTranslator(NullLiteral.class);

	public NullLiteral(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return translator.i18n("Null Literal");
	}

}
