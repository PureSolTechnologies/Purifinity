package com.puresol.coding.lang.cpp.source.tokengroups;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.cpp.source.literals.BooleanLiteral;
import com.puresol.coding.lang.cpp.source.literals.CharacterLiteral;
import com.puresol.coding.lang.cpp.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.cpp.source.literals.IdLiteral;
import com.puresol.coding.lang.cpp.source.literals.IntegerLiteral;
import com.puresol.coding.lang.cpp.source.literals.StringLiteral;
import com.puresol.parser.AbstractTokenDefinitionGroup;
import com.puresol.parser.TokenException;

public class CPPLiterals extends AbstractTokenDefinitionGroup {

	public static final CPPLiterals INSTANCE = new CPPLiterals();

	private static final Logger logger = Logger.getLogger(CPPLiterals.class);

	@Override
	protected void initialize() {
		try {
			addTokenDefinition(FloatingPointLiteral.class);
			addTokenDefinition(IntegerLiteral.class);
			addTokenDefinition(CharacterLiteral.class);
			addTokenDefinition(StringLiteral.class);
			addTokenDefinition(BooleanLiteral.class);

			addTokenDefinition(IdLiteral.class);
		} catch (TokenException e) {
			logger.error(e.getMessage());
		}
	}
}
