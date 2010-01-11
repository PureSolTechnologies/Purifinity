package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class ReturnKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "return";
    }

}
