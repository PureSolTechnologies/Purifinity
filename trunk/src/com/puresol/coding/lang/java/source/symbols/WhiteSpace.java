package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.lang.java.LexicalStructure;
import com.puresol.coding.tokentypes.Hidden;

/**
 * These is a representation of Java's white space collection. This is complete
 * after "Java Language Specification-3.0".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class WhiteSpace extends Hidden {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString(LexicalStructure.WHITE_SPACE);
	}

}
