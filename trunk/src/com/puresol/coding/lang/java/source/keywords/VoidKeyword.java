package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperant;

/**
 * This is a keyword of Java Programming Language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class VoidKeyword extends KeywordOperant {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("void");
	}

}
