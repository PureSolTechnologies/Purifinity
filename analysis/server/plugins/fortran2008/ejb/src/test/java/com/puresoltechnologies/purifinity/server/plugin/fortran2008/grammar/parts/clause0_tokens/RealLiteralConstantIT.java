package com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.parts.clause0_tokens;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.parsers.grammar.token.TokenDefinition;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammar;

public class RealLiteralConstantIT {

	@Test
	public void test() {
		TokenDefinition definition;
		definition = FortranGrammar.getInstance().getTokenDefinitions()
				.getDefinition("REAL_LITERAL_CONSTANT");
		assertTrue(definition.getPattern().matcher("1.23").matches());
		assertTrue(definition.getPattern().matcher("1. ").find());
		assertTrue(definition.getPattern().matcher("1.E2").matches());
		assertTrue(definition.getPattern().matcher("1.23E45").matches());
		assertTrue(definition.getPattern().matcher("1.23E+45").matches());
		assertTrue(definition.getPattern().matcher("1.23E-45").matches());
		assertTrue(definition.getPattern().matcher("1. ").find());
		assertTrue(definition.getPattern().matcher("1.-").find());
		assertFalse(definition.getPattern().matcher("1 ").find());
	}

}
