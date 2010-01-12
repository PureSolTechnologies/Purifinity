package com.puresol.coding.java.tokengroups;

import com.puresol.coding.java.literals.BooleanLiteral;
import com.puresol.coding.java.literals.CharacterLiteral;
import com.puresol.coding.java.literals.FloatingPointLiteral;
import com.puresol.coding.java.literals.IdLiteral;
import com.puresol.coding.java.literals.IntegerLiteral;
import com.puresol.coding.java.literals.StringLiteral;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class JavaLiterals extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {
	addKeyword(FloatingPointLiteral.class);
	addKeyword(IntegerLiteral.class);
	addKeyword(CharacterLiteral.class);
	addKeyword(StringLiteral.class);
	addKeyword(BooleanLiteral.class);

	addKeyword(IdLiteral.class);
    }
}
