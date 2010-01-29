package com.puresol.coding.lang.cpp.source.tokengroups;

import com.puresol.coding.lang.cpp.source.literals.BooleanLiteral;
import com.puresol.coding.lang.cpp.source.literals.CharacterLiteral;
import com.puresol.coding.lang.cpp.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.cpp.source.literals.IdLiteral;
import com.puresol.coding.lang.cpp.source.literals.IntegerLiteral;
import com.puresol.coding.lang.cpp.source.literals.StringLiteral;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class CPPLiterals extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {
	addTokenDefinition(FloatingPointLiteral.class);
	addTokenDefinition(IntegerLiteral.class);
	addTokenDefinition(CharacterLiteral.class);
	addTokenDefinition(StringLiteral.class);
	addTokenDefinition(BooleanLiteral.class);

	addTokenDefinition(IdLiteral.class);
    }
}
