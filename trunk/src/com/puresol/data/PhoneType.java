package com.puresol.data;

import javax.i18n4j.Translator;

public enum PhoneType implements Identifiable {

    UNKNOWN, LANDLINE, MOBILE;

    private static final Translator translator =
	    Translator.getTranslator(PhoneType.class);

    public static PhoneType getDefault() {
	return UNKNOWN;
    }

    @Override
    public String getIdentifier() {
	if (this == LANDLINE) {
	    return translator.i18n("landline");
	}
	if (this == MOBILE) {
	    return translator.i18n("landline");
	}
	if (this == UNKNOWN) {
	    return translator.i18n("unknown");
	}
	return null;
    }
}
