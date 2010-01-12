package com.puresol.coding.java.source.symbols;

import com.puresol.coding.tokentypes.BlockBegin;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

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
	public boolean countForHalstead(Token token, TokenStream tokenStream) {
		return true;
	}
}
