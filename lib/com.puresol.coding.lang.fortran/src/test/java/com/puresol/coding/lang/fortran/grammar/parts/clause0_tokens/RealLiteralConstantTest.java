package com.puresol.coding.lang.fortran.grammar.parts.clause0_tokens;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.utils.PersistenceException;

public class RealLiteralConstantTest {

	@Test
	public void test() {
		TokenDefinition definition;
		try {
			definition = FortranGrammar.getInstance().getGrammar()
					.getTokenDefinitions()
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
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
