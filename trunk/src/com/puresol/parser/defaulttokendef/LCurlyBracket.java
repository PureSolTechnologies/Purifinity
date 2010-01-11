package com.puresol.parser.defaulttokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class LCurlyBracket extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "\\{";
    }

}
