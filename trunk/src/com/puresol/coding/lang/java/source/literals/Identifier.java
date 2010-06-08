package com.puresol.coding.lang.java.source.literals;

import com.puresol.coding.lang.java.LexicalStructure;
import com.puresol.coding.tokentypes.Literal;

/**
 * This is a Java Programming Language identifier.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Identifier extends Literal {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString(LexicalStructure.IDENTIFIER);
	}

}
