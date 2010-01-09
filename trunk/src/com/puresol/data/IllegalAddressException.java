package com.puresol.data;

import javax.i18n4j.Translator;

public class IllegalAddressException extends Exception {

	private static final long serialVersionUID = 3573127478944891962L;

	private static final Translator translator = Translator
			.getTranslator(IllegalAddressException.class);

	public IllegalAddressException(Address address) {
		super(translator.i18n("Address \"{0}\" is invalid!", address));
	}

}
