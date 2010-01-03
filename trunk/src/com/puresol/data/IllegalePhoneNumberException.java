package com.puresol.data;

import javax.i18n4j.Translator;

public class IllegalePhoneNumberException extends IllegalArgumentException {

	private static final long serialVersionUID = -2068634450697134254L;

	private static final Translator translator = Translator
			.getTranslator(IllegalePhoneNumberException.class);

	public IllegalePhoneNumberException() {
		super();
	}

	public IllegalePhoneNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalePhoneNumberException(String s) {
		super(s);
	}

	public IllegalePhoneNumberException(Throwable cause) {
		super(cause);
	}

	public IllegalePhoneNumberException(PhoneNumber phone) {
		super(translator.i18n("Phone number '{0}' is invalid!", phone
				.toString()));
	}
}
