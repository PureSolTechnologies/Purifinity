package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Hidden;

/**
 * This class is a representation of Java's line break collection. It's complete
 * after "Java Language Specification 3.0".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LineBreak extends Hidden {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("(\\r\\n|\\n|\\r)");
	}

}
