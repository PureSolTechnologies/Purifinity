package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class PublicKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "public";
    }

}
