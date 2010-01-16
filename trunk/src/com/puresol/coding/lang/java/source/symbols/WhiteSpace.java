package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Hidden;

public class WhiteSpace extends Hidden {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("( |\\t)");
	}

}
