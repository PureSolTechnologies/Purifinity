package com.puresol.parser.defaulttokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class RParen extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "\\)";
    }

}
