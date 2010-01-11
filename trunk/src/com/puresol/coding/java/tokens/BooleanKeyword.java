package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class BooleanKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "boolean";
    }

}
