package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class ImplementsKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "implements";
    }

}
