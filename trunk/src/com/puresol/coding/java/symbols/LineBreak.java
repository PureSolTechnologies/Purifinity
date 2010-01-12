package com.puresol.coding.java.symbols;

import com.puresol.coding.tokentypes.Hidden;

public class LineBreak extends Hidden {

	@Override
	public String getPatternString() {
		return "(\\r\\n|\\n)";
	}

}
