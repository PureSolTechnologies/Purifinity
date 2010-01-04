package com.puresol.data;

/**
 * This Enumeration specifies the kind of a part of a name.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum PersonNamePartType {

    LAST_NAME("L"), MIDDLE_NAME("M"), FIRST_NAME("F"), NAME_EXTENSION("E");

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
}
