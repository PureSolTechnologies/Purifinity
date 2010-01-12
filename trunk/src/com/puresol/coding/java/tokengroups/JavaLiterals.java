package com.puresol.coding.java.tokengroups;

import com.puresol.coding.java.tokens.BooleanLiteral;
import com.puresol.coding.java.tokens.CharacterLiteral;
import com.puresol.coding.java.tokens.FloatingPointLiteral;
import com.puresol.coding.java.tokens.IdLiteral;
import com.puresol.coding.java.tokens.IntegerLiteral;
import com.puresol.coding.java.tokens.StringLiteral;
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
