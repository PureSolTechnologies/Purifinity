package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class ShortKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "short";
    }

}
