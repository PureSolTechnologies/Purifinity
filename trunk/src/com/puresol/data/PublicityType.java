package com.puresol.data;

import javax.i18n4java.Translator;

public enum PublicityType implements Identifiable {

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

	@Override
	public String getIdentifier() {
		if (this == UNKNOWN) {
			return translator.i18n("unknown");
		}
		if (this == BUSINESS) {
			return translator.i18n("business");
		}
		if (this == PRIVATE) {
			return translator.i18n("private");
		}
		return null;
	}
}
