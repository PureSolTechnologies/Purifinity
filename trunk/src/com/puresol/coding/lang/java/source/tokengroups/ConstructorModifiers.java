package com.puresol.coding.lang.java.source.tokengroups;

import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class ConstructorModifiers extends AbstractTokenDefinitionGroup {

	@Override
	protected void initialize() {
		addKeyword(PublicKeyword.class);
		addKeyword(ProtectedKeyword.class);
		addKeyword(PrivateKeyword.class);
		addKeyword(FinalKeyword.class);
	}

}
