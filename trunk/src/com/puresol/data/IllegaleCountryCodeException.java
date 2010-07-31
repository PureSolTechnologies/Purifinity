package com.puresol.data;

import javax.i18n4java.Translator;

/**
 * This exception is thrown in case of the usage of a illegal ISO country code.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IllegaleCountryCodeException extends Exception {

	private static final long serialVersionUID = -5137347940995440970L;

	private static final Translator translator = Translator
			.getTranslator(IllegaleCountryCodeException.class);

	public IllegaleCountryCodeException(String isoCountry) {
		super(translator.i18n("Country code \"{0}\" is invalid!", isoCountry));
	}

	public IllegaleCountryCodeException(Country country) {
		super(translator.i18n("Country \"{0}\" is invalid!", country));
	}

}
