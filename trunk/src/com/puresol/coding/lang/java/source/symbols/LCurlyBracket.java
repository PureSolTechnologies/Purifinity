package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.BlockBegin;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class LCurlyBracket extends BlockBegin {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("\\{");
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
