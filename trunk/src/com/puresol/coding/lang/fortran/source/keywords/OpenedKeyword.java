package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.Operator;

public class OpenedKeyword extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("OPENED");
	}

}
