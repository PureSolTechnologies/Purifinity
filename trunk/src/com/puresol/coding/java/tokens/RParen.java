package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operator;

public class RParen extends Operator {

	@Override
	public String getPatternString() {
		return "\\)";
	}

	@Override
	public String getHalsteadSymbol() {
		return "()";
	}

	@Override
	public boolean countForHalstead() {
		return false;
	}

}
