package com.puresol.coding.lang.fortran.source.tokengroups;

import com.puresol.coding.lang.fortran.source.keywords.SubRoutineKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class FortranKeywords extends AbstractTokenDefinitionGroup {

	@Override
	protected void initialize() {
		addKeyword(SubRoutineKeyword.class);
	}
}
