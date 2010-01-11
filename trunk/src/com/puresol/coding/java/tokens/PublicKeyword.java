package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class PublicKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "public";
    }

}
