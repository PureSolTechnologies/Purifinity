package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operator;

public class Assign extends Operator {

	@Override
	public String getPatternString() {
		return "=";
	}

}
