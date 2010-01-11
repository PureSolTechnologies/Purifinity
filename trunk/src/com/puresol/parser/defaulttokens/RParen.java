package com.puresol.parser.defaulttokens;

import com.puresol.parser.AbstractTokenDefinition;

public class RParen extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "\\)";
    }

}
