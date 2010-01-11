package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class FloatKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "float";
    }

}
