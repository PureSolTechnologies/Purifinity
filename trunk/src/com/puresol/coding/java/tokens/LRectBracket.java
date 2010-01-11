package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operator;

public class LRectBracket extends Operator {

	@Override
	public String getPatternString() {
		return "\\[";
	}

	@Override
	public String getHalsteadSymbol() {
		return "[]";
	}

	@Override
	public boolean countForHalstead() {
		return true;
	}

}
