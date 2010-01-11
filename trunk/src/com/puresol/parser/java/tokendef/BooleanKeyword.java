package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class BooleanKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "boolean";
    }

}
