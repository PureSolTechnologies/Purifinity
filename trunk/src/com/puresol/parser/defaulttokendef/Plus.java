package com.puresol.parser.defaulttokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class Plus extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "\\+";
    }

}
