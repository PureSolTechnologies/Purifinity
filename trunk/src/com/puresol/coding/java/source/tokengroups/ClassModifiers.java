package com.puresol.coding.java.source.tokengroups;

import com.puresol.coding.java.source.keywords.AbstractKeyword;
import com.puresol.coding.java.source.keywords.FinalKeyword;
import com.puresol.coding.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.java.source.keywords.PublicKeyword;
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
