package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;

public class Assign extends Operator {

	@Override
	public String getPatternString() {
		return "=";
	}

}
