package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;

public class GreaterThanGreaterThanGreaterThan extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString(">>>");
	}

}
