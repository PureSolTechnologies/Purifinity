package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class ReturnKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "return";
    }

}
