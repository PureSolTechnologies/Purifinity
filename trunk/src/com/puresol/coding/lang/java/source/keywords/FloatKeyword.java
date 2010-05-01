package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

/**
 * This is a keyword of Java Programming Language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FloatKeyword extends PrimitiveDataType {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("float");
	}

}
