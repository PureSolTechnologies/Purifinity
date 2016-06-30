package com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.parts.clause0_tokens;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.parsers.grammar.token.TokenDefinition;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammar;

public class CharLiteralConstantIT {

	@Test
	public void test() {
		TokenDefinition definition;
		definition = FortranGrammar.getInstance().getTokenDefinitions()
				.getDefinition("CHAR_LITERAL_CONSTANT");
		assertTrue(definition.getPattern().matcher("'String'").find());
		assertTrue(definition.getPattern().matcher("\"String\"").find());
		assertFalse(definition.getPattern().matcher("\"String").find());
		assertFalse(definition.getPattern().matcher("'String").find());
		assertFalse(definition.getPattern()
				.matcher("'X-COORDINATES IN THE HEXAGONAL CASSETTE ").find());
	}

}
