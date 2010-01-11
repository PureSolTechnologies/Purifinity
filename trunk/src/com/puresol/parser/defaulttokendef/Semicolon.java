package com.puresol.parser.defaulttokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class Semicolon extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return ";";
    }

}
