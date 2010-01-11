package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class ByteKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "byte";
    }

}
