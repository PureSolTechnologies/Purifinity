package com.puresol.uhura.ust.types;

import javax.i18n4java.Translator;

public class StringType extends Type {

	private static final long serialVersionUID = 7805628949907717406L;

	private static final Translator translator = Translator
			.getTranslator(StringType.class);

	public StringType(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return translator.i18n("String Type");
	}

}
