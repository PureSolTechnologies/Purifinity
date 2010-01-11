package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class StaticKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "static";
    }

}
