package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class VoidKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "void";
    }

}
