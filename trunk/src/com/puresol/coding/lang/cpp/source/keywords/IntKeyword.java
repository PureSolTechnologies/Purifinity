package com.puresol.coding.lang.cpp.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class IntKeyword extends PrimitiveDataType {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("integer");
	}

}
