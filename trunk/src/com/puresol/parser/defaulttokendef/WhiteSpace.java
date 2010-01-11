package com.puresol.parser.defaulttokendef;

import com.puresol.parser.AbstractTokenDefinition;
import com.puresol.parser.TokenPublicity;

public class WhiteSpace extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	setPublicity(TokenPublicity.HIDDEN);
	return "( |\\t)";
    }

}
