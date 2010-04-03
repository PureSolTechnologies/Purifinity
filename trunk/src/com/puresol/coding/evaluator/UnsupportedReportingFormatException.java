package com.puresol.coding.evaluator;

import javax.i18n4j.Translator;

import com.puresol.reporting.ReportingFormat;

public class UnsupportedReportingFormatException extends Exception {

	private static final long serialVersionUID = -4781856011865619824L;

	private static final Translator translator = Translator
			.getTranslator(UnsupportedReportingFormatException.class);

	public UnsupportedReportingFormatException(ReportingFormat format) {
		super(translator.i18n("Reporting format '" + format.getIdentifier()
				+ "' is not supported!"));
	}

}
