package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class BreakKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "break";
    }

}
