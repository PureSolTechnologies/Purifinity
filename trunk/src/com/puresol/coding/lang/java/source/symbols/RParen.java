package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class RParen extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("\\)");
	}

	@Override
	public String getHalsteadSymbol() {
		return "()";
	}

	@Override
	public boolean countForHalstead(Token token, TokenStream tokenStream) {
		return false;
	}

}
