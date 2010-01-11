package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class PrivateKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "private";
    }

}
