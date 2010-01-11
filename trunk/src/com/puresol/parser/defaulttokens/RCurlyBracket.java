package com.puresol.parser.defaulttokens;

import com.puresol.parser.AbstractTokenDefinition;

public class RCurlyBracket extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "\\}";
    }

}
