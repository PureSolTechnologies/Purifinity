package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class ReturnKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "return";
    }

}
