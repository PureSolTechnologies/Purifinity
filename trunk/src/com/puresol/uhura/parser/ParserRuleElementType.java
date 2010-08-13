package com.puresol.uhura.parser;

public enum ParserRuleElementType {

	PART, TOKEN, TEXT;

	@Override
	public String toString() {
		return name();
	}

}
