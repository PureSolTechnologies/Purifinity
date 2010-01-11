package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class ExtendsKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "extends";
    }

}
