package com.puresol.parser.defaulttokens;

import com.puresol.parser.AbstractTokenDefinition;
import com.puresol.parser.TokenPublicity;

public class LineBreak extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	setPublicity(TokenPublicity.HIDDEN);
	return "(\\r\\n|\\n)";
    }

}
