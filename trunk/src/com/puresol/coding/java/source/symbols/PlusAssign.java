package com.puresol.coding.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;

public class PlusAssign extends Operator {

	@Override
	public String getPatternString() {
		return "\\+=";
	}

}
