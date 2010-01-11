package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operator;

public class RRectBracket extends Operator {

	@Override
	public String getPatternString() {
		return "\\]";
	}

	@Override
	public String getHalsteadSymbol() {
		return "[]";
	}

	@Override
	public boolean countForHalstead() {
		return false;
	}

}
