package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class DoubleKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "double";
    }

}
