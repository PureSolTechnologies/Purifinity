package com.puresol.coding.lang.fortran.source.symbols;

import com.puresol.coding.tokentypes.Hidden;

public class LineLead extends Hidden {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("......");
	}

}
