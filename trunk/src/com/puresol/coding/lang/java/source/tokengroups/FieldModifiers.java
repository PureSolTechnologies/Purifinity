package com.puresol.coding.lang.java.source.tokengroups;

import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.keywords.TransientKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class FieldModifiers extends AbstractTokenDefinitionGroup {

	@Override
	protected void initialize() {
		addTokenDefinition(PublicKeyword.class);
		addTokenDefinition(ProtectedKeyword.class);
		addTokenDefinition(PrivateKeyword.class);
		addTokenDefinition(FinalKeyword.class);
		addTokenDefinition(StaticKeyword.class);
		addTokenDefinition(TransientKeyword.class);
	}

}
