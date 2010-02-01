package com.puresol.coding.lang.fortran.source.symbols;

import com.puresol.coding.tokentypes.Operator;

public class LessThan extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("<");
	}

}
