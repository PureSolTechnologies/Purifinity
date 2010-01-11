package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class PrivateKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "private";
    }

}
