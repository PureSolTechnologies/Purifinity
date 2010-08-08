package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.keywords.TransientKeyword;
import com.puresol.coding.lang.java.source.keywords.VolatileKeyword;
import com.puresol.parser.tokens.TokenDefinition;

public class FieldModifiers {

	public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

	static {
		DEFINITIONS.add(PublicKeyword.class);
		DEFINITIONS.add(ProtectedKeyword.class);
		DEFINITIONS.add(PrivateKeyword.class);
		DEFINITIONS.add(FinalKeyword.class);
		DEFINITIONS.add(StaticKeyword.class);
		DEFINITIONS.add(TransientKeyword.class);
		DEFINITIONS.add(VolatileKeyword.class);
	}

}
