package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;

/**
 * This is a keyword of Java Programming Language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FinalKeyword extends KeywordOperator {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("final");
	}

}
