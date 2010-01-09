package com.puresol.data;

import javax.i18n4j.Translator;

public class MailformedEMailException extends Exception {

	private static final long serialVersionUID = 8623558485226968692L;

	private static final Translator translator = Translator
			.getTranslator(MailformedEMailException.class);

	public MailformedEMailException(String eMailAddress) {
		super(translator
				.i18n("eMail address \"{0}\" is invalid!", eMailAddress));
	}

	public MailformedEMailException(EMailAddress eMailAddress) {
		super(translator.i18n("eMail address \"{0}\" is invalid!", eMailAddress
				.toString()));
	}

}
