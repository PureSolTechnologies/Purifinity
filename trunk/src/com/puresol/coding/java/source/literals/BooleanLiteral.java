package com.puresol.coding.java.source.literals;

import com.puresol.coding.tokentypes.Operant;

public class BooleanLiteral extends Operant {

	@Override
	public String getPatternString() {
		return "(true|false)";
	}

}
