package com.puresol.uhura.ust.modifiers;

import javax.i18n4java.Translator;

public class PublicModifier extends Modifier {

	private static final long serialVersionUID = -5800466279299023817L;

	private static final Translator translator = Translator
			.getTranslator(PublicModifier.class);

	public PublicModifier(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return translator.i18n("Public Modifier");
	}

}
