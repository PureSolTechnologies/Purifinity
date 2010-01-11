package com.puresol.parser.defaulttokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class LParen extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "\\(";
    }

}
