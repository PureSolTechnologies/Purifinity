package com.puresol.data;

import javax.i18n4j.Translator;

public enum PublicityType {

	UNKNOWN, BUSINESS, PRIVATE;

	private static final Translator translator = Translator
			.getTranslator(PublicityType.class);

	public String getDisplayString() {
		if (this == BUSINESS) {
			return translator.i18n("business");
		}
		if (this == PRIVATE) {
			return translator.i18n("private");
		}
		return translator.i18n("unknown");
	}

	public static PublicityType getDefault() {
		return UNKNOWN;
	}
}
