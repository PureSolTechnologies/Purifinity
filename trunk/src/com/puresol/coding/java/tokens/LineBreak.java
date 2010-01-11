package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Hidden;

public class LineBreak extends Hidden {

	@Override
	public String getPatternString() {
		return "(\\r\\n|\\n)";
	}

}
