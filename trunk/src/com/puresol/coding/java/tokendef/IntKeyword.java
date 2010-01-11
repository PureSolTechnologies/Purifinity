package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class IntKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "integer";
    }

}
