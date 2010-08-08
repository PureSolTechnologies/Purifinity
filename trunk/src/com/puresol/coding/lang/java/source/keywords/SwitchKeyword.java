package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenStream;

/**
 * This is a keyword of Java Programming Language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SwitchKeyword extends KeywordOperator {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("switch");
	}

	@Override
	public String getHalsteadSymbol() {
		return "switch()";
	}

	@Override
	public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
		return 1;
	}
}
