package com.puresol.coding.lang.java.source.tokengroups;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.java.source.literals.BooleanLiteral;
import com.puresol.coding.lang.java.source.literals.CharacterLiteral;
import com.puresol.coding.lang.java.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.literals.IntegerLiteral;
import com.puresol.coding.lang.java.source.literals.StringLiteral;
import com.puresol.parser.AbstractTokenDefinitionGroup;
import com.puresol.parser.TokenException;

public class JavaLiterals extends AbstractTokenDefinitionGroup {

	public static final JavaLiterals INSTANCE = new JavaLiterals();

	private static final Logger logger = Logger.getLogger(JavaLiterals.class);

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
