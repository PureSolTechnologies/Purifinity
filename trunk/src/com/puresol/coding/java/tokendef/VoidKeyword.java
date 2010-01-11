package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class VoidKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "void";
    }

}
