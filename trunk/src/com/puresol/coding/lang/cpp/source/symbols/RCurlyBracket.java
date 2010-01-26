package com.puresol.coding.lang.cpp.source.symbols;

import com.puresol.coding.tokentypes.BlockEnd;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class RCurlyBracket extends BlockEnd {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("\\}");
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
