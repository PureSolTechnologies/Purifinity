package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class ClassKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "class";
    }

}
