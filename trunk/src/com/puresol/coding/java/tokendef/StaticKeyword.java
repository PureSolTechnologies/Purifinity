package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class StaticKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "static";
    }

}
