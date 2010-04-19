package com.puresol.coding.lang.cpp.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.cpp.source.literals.BooleanLiteral;
import com.puresol.coding.lang.cpp.source.literals.CharacterLiteral;
import com.puresol.coding.lang.cpp.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.cpp.source.literals.IdLiteral;
import com.puresol.coding.lang.cpp.source.literals.IntegerLiteral;
import com.puresol.coding.lang.cpp.source.literals.StringLiteral;
import com.puresol.parser.TokenDefinition;

public class CPPLiterals {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(FloatingPointLiteral.class);
	DEFINITIONS.add(IntegerLiteral.class);
	DEFINITIONS.add(CharacterLiteral.class);
	DEFINITIONS.add(StringLiteral.class);
	DEFINITIONS.add(BooleanLiteral.class);

	DEFINITIONS.add(IdLiteral.class);
    }
}
