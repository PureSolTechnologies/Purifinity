package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class ByteKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "byte";
    }

}
