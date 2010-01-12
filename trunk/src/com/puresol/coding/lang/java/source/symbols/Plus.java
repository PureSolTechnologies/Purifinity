package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;

public class Plus extends Operator {

	@Override
	public String getPatternString() {
		return "\\+";
	}

}
