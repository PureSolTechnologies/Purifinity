package com.puresol.coding.java.tokengroups;

import com.puresol.coding.java.keywords.AbstractKeyword;
import com.puresol.coding.java.keywords.FinalKeyword;
import com.puresol.coding.java.keywords.ProtectedKeyword;
import com.puresol.coding.java.keywords.PublicKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class ClassModifiers extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {
	addKeyword(PublicKeyword.class);
	addKeyword(ProtectedKeyword.class);
	addKeyword(FinalKeyword.class);
	addKeyword(AbstractKeyword.class);
    }

}
