package com.puresol.data;

import javax.i18n4j.Translator;

public class IllegalePhoneNumberException extends Exception {

    private static final long serialVersionUID = -2068634450697134254L;

    private static final Translator translator =
	    Translator.getTranslator(IllegalePhoneNumberException.class);

    public IllegalePhoneNumberException(PhoneNumber phone) {
	super(translator.i18n("Phone number \"{0}\" is invalid!", phone
		.toString()));
    }
}
