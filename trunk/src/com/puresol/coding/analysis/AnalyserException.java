package com.puresol.coding.analysis;

import javax.i18n4java.Translator;

public class AnalyserException extends Exception {

	private static final long serialVersionUID = 8839355501039462048L;

	private static final Translator translator = Translator
			.getTranslator(AnalyserException.class);

	public AnalyserException(Analyzer analyser) {
		super(translator.i18n("Analyser {0} is not suitable for file {1}",
				analyser.getClass(), analyser.getFile()));
	}
}
