package com.puresol.data;


public enum PublicityType implements Identifiable {

    UNKNOWN, BUSINESS, PRIVATE;

    public String getDisplayString() {
	if (this == BUSINESS) {
	    return "business";
	}
	if (this == PRIVATE) {
	    return "private";
	}
	return "unknown";
    }

    public static PublicityType getDefault() {
	return UNKNOWN;
    }

    @Override
    public String getIdentifier() {
	if (this == UNKNOWN) {
	    return "unknown";
	}
	if (this == BUSINESS) {
	    return "business";
	}
	if (this == PRIVATE) {
	    return "private";
	}
	return null;
    }
}
