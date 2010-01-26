package com.puresol.coding.lang.cpp.source.literals;

import com.puresol.coding.tokentypes.Literal;

public class BooleanLiteral extends Literal {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("(true|false)");
	}

}
