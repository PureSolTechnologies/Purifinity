package com.puresol.data;

import javax.i18n4j.Translator;

public enum PhoneType {

	UNKNOWN, LANDLINE, MOBILE;

	private static final Translator translator = Translator
			.getTranslator(PhoneType.class);

	public String getDisplayString() {
		if (this == LANDLINE) {
			return translator.i18n("landline");
		}
		if (this == MOBILE) {
			return translator.i18n("landline");
		}
		return translator.i18n("unknown");
	}

	public static PhoneType getDefault() {
		return UNKNOWN;
	}
}
