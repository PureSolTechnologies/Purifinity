package com.puresol.uhura.ust.modifiers;

import javax.i18n4java.Translator;

public class ProtectedModifier extends Modifier {

	private static final long serialVersionUID = 729411154443449805L;

	private static final Translator translator = Translator
			.getTranslator(ProtectedModifier.class);

	public ProtectedModifier(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return translator.i18n("Protected Modifier");
	}

}
