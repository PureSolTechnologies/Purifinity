package com.puresol.coding.lang.java.source.literals;

import com.puresol.coding.tokentypes.KeywordOperant;

/**
 * This is a Java Programming Language Literal.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NullLiteral extends KeywordOperant {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("null");
	}

}
