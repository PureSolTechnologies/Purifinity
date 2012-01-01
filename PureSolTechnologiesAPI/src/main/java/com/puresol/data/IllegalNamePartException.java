package com.puresol.data;

import javax.i18n4java.Translator;

public class IllegalNamePartException extends Exception {

	private static final long serialVersionUID = 5714017686556808402L;

	private static final Translator translator = Translator
			.getTranslator(IllegalNamePartException.class);

	public IllegalNamePartException(PersonNamePart namePart) {
		super(translator.i18n("Name part \"{0}\" is invalid!",
				namePart.toString()));
	}

}
