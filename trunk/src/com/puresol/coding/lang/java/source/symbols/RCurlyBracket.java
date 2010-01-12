package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.BlockEnd;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

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
	public boolean countForHalstead(Token token, TokenStream tokenStream) {
		return false;
	}

}
