package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class SynchronizedKeyword extends PrimitiveDataType {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("synchronized");
	}

}
