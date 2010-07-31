package com.puresol.reporting;

import javax.i18n4java.Translator;

public class UnsupportedFormatException extends Exception {

	private static final long serialVersionUID = -4781856011865619824L;

	private static final Translator translator = Translator
			.getTranslator(UnsupportedFormatException.class);

	public UnsupportedFormatException(ReportingFormat format) {
		super(translator.i18n("Reporting format '" + format.getIdentifier()
				+ "' is not (yet) supported!"));
	}

}
