package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.Operator;

public class DotFALSEDot extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("\\.FALSE\\.");
	}

}
