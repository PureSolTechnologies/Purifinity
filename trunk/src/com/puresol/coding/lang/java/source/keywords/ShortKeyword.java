package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class ShortKeyword extends PrimitiveDataType {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("short");
	}

}
