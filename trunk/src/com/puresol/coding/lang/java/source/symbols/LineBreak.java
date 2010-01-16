package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Hidden;

public class LineBreak extends Hidden {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("(\\r\\n|\\n)");
	}

}
