package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class BooleanKeyword extends PrimitiveDataType {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("boolean");
	}
}
