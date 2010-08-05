package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class ImportKeyword extends PrimitiveDataType {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("IMPORT");
	}

}
