package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class PublicKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "public";
    }

}
