package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class MinusAssign extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "-=";
    }

}
