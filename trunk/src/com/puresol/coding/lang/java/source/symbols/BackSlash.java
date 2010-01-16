package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;

public class BackSlash extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("/");
	}

}
