package com.puresol.coding.lang.java.source.literals;

import com.puresol.coding.lang.java.LexicalStructure;
import com.puresol.coding.tokentypes.Literal;

/**
 * This is a Java Programming Language Literal.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BooleanLiteral extends Literal {

	@Override
	protected void initialize() {
		super.initialize();
		setLookAheadPatternString("(?!\\w)");
		setPatternString(LexicalStructure.BOOLEAN_LITERAL);
	}

}
