package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class PackageKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "package";
    }

}
