package com.puresol.coding.lang.fortran.source.tokengroups;

import com.puresol.coding.lang.fortran.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.fortran.source.literals.IntegerLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringLiteral;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class FortranLiterals extends AbstractTokenDefinitionGroup {

	@Override
	protected void initialize() {
		addTokenDefinition(StringLiteral.class);
		addTokenDefinition(FloatingPointLiteral.class);
		addTokenDefinition(IntegerLiteral.class);

		addTokenDefinition(IdLiteral.class);
	}
}
