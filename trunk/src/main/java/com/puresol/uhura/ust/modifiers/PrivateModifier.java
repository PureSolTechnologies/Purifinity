package com.puresol.uhura.ust.modifiers;

import javax.i18n4java.Translator;

public class PrivateModifier extends Modifier {

	private static final long serialVersionUID = -4713670533029137280L;

	private static final Translator translator = Translator
			.getTranslator(PrivateModifier.class);

	public PrivateModifier(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return translator.i18n("Private Modifier");
	}

}
