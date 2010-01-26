package com.puresol.coding.lang.cpp.source.literals;

import com.puresol.coding.tokentypes.Literal;

public class IntegerLiteral extends Literal {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("\\d+L?");
	}

}
