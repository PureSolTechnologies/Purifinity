package com.puresol.coding.lang.java.source.literals;

import com.puresol.coding.tokentypes.Operant;

public class IdLiteral extends Operant {

	@Override
	public String getPatternString() {
		return "[a-zA-Z_]([a-zA-Z_0-9])*";
	}

}
