package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenStream;

public class LParen extends Operator {
	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("\\(");
	}

	@Override
	public String getHalsteadSymbol() {
		return "()";
	}

	@Override
	public boolean countForHalstead(Token token, TokenStream tokenStream) {
		return true; // TODO think about the right way to implement this!
	}
}
