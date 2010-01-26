package com.puresol.coding.lang.cpp.source.symbols;

import com.puresol.coding.tokentypes.Operator;

public class Semicolon extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString(";");
	}

}
