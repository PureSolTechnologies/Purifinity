package com.puresol.coding.lang.fortran.source.symbols;

import com.puresol.coding.tokentypes.Hidden;

public class WhiteSpace extends Hidden {

	@Override
	public String getPatternString() {
		return "( |\\t)";
	}

}
