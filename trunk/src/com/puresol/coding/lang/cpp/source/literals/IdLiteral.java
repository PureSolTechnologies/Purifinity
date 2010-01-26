package com.puresol.coding.lang.cpp.source.literals;

import com.puresol.coding.tokentypes.Literal;

public class IdLiteral extends Literal {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("[a-zA-Z_]([a-zA-Z_0-9])*");
	}

}
