package com.puresol.reporting;

import javax.i18n4j.Translator;

import com.puresol.data.Identifiable;

/**
 * This enumeration specifies the output format for the document to be
 * generated.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum ReportingFormat implements Identifiable {
	TEXT {

		@Override
		public String getIdentifier() {
			return translator.i18n("Plain text output.");
		}
	},
	HTML {

		@Override
		public String getIdentifier() {
			return translator.i18n("HTML output.");
		}
	};

	private static final Translator translator = Translator
			.getTranslator(ReportingFormat.class);

	@Override
	abstract public String getIdentifier();
}
