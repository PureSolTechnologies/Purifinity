package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operator;

public class PlusPlus extends Operator {

	@Override
	public String getPatternString() {
		return "\\+\\+";
	}

}
