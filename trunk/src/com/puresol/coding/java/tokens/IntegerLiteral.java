package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operant;

public class IntegerLiteral extends Operant {

	@Override
	public String getPatternString() {
		return "\\d+";
	}

}
