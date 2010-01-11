package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.BlockEnd;

public class RCurlyBracket extends BlockEnd {

	@Override
	public String getPatternString() {
		return "\\}";
	}

	@Override
	public String getHalsteadSymbol() {
		return "{}";
	}

	@Override
	public boolean countForHalstead() {
		return false;
	}

}
