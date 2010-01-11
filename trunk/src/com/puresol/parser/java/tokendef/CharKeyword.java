package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class CharKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "char";
    }

}
