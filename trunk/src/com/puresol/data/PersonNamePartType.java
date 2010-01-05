package com.puresol.data;

import javax.i18n4j.Translator;

/**
 * This Enumeration specifies the kind of a part of a name.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum PersonNamePartType implements Identifiable {

    LAST_NAME("L"), MIDDLE_NAME("M"), FIRST_NAME("F"), NAME_EXTENSION("E");

    private static final Translator translator =
	    Translator.getTranslator(PersonNamePartType.class);

    private String symbol;

    private PersonNamePartType(String symbol) {
	this.symbol = symbol;
    }

    public String getSymbol() {
	return symbol;
    }

    public static PersonNamePartType from(String symbol) {
	if (symbol != null) {
	    PersonNamePartType[] types =
		    PersonNamePartType.class.getEnumConstants();
	    for (PersonNamePartType type : types) {
		if (symbol.equalsIgnoreCase(type.getSymbol())) {
		    return type;
		}
	    }
	}
	throw new IllegalArgumentException("Symbol '" + symbol
		+ "' is invalid!");
    }

    @Override
    public String getIdentifier() {
	if (this == LAST_NAME) {
	    return translator.i18n("last name");
	}
	if (this == MIDDLE_NAME) {
	    return translator.i18n("middle name");
	}
	if (this == FIRST_NAME) {
	    return translator.i18n("first name");
	}
	if (this == NAME_EXTENSION) {
	    return translator.i18n("name extension");
	}
	return null;
    }
}
