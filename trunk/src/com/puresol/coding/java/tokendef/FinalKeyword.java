package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class FinalKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "final";
    }

}
