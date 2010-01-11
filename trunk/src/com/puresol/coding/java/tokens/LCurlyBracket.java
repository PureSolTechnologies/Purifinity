package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.BlockBegin;

public class LCurlyBracket extends BlockBegin {

	@Override
	public String getPatternString() {
		return "\\{";
	}

	@Override
	public String getHalsteadSymbol() {
		return "{}";
	}

	@Override
	public boolean countForHalstead() {
		return true;
	}
}
