package com.puresol.coding.lang.java.source.tokengroups;

import com.puresol.coding.lang.java.source.keywords.BooleanKeyword;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CharKeyword;
import com.puresol.coding.lang.java.source.keywords.IntKeyword;
import com.puresol.coding.lang.java.source.keywords.LongKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class Primitives extends AbstractTokenDefinitionGroup {

	@Override
	protected void initialize() {
		addTokenDefinition(BooleanKeyword.class);
		addTokenDefinition(CharKeyword.class);
		addTokenDefinition(ByteKeyword.class);
		addTokenDefinition(ShortKeyword.class);
		addTokenDefinition(IntKeyword.class);
		addTokenDefinition(LongKeyword.class);
		addTokenDefinition(VoidKeyword.class);
	}
}
