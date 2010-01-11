package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operant;

public class FloatingPointLiteral extends Operant {

	@Override
	public String getPatternString() {
		return "(\\+|-)?\\d*\\.\\d+((e|E)(\\+|-)?\\d+)?";
	}

}
