package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class TransientKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "transient";
    }

}
