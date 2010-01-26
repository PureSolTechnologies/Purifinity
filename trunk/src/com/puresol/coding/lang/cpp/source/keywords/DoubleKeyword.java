package com.puresol.coding.lang.cpp.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class DoubleKeyword extends PrimitiveDataType {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("double");
	}

}
