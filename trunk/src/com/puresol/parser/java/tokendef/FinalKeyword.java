package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class FinalKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "final";
    }

}
