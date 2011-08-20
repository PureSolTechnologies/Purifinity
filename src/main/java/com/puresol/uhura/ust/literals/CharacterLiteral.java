package com.puresol.uhura.ust.literals;

import javax.i18n4java.Translator;

public class CharacterLiteral extends NumericalLiteral {

	private static final long serialVersionUID = -5622529938750219170L;

	private static final Translator translator = Translator
			.getTranslator(CharacterLiteral.class);

	private final char value;

	public CharacterLiteral(String originalSymbol, char value) {
		super(originalSymbol);
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	@Override
	public String getName() {
		return translator.i18n("Character Literal: {0}", value);
	}

}
