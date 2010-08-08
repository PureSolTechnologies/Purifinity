package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.parser.tokens.TokenDefinition;

public class ConstantModifiers {

	public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

	static {
		DEFINITIONS.add(PublicKeyword.class);
		DEFINITIONS.add(StaticKeyword.class);
		DEFINITIONS.add(FinalKeyword.class);
	}

}
