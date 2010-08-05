package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class EndInterfaceKeyword extends PrimitiveDataType {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("END\\s*INTERFACE");
	}

}
