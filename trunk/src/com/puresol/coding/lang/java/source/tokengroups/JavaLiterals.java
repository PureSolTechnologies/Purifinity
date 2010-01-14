package com.puresol.coding.lang.java.source.tokengroups;

import com.puresol.coding.lang.java.source.literals.BooleanLiteral;
import com.puresol.coding.lang.java.source.literals.CharacterLiteral;
import com.puresol.coding.lang.java.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.literals.IntegerLiteral;
import com.puresol.coding.lang.java.source.literals.StringLiteral;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class JavaLiterals extends AbstractTokenDefinitionGroup {

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
