package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

/**
 * This is a keyword of Java Programming Language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IfKeyword extends KeywordOperator {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("if");
	}

	@Override
	public String getHalsteadSymbol() {
		return "if()";
	}

	@Override
	public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
		return 1;
	}
}
