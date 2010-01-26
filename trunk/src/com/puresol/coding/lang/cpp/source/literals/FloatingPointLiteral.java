package com.puresol.coding.lang.cpp.source.literals;

import com.puresol.coding.tokentypes.Literal;

public class FloatingPointLiteral extends Literal {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("(\\+|-)?\\d*\\.\\d+((e|E)(\\+|-)?\\d+)?(?!\\w)");
	}

}
