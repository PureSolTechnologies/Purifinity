package com.puresol.data;

import javax.i18n4java.Translator;

public class IllegalCompanyNameException extends Exception {

	private static final long serialVersionUID = -495672131438469508L;

	private static final Translator translator = Translator
			.getTranslator(IllegalCompanyNameException.class);

	public IllegalCompanyNameException(CompanyName companyName) {
		super(translator.i18n("Company name \"{0}\" is invalid!", companyName));
	}

}
