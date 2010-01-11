package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class FinalKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "final";
    }

}
