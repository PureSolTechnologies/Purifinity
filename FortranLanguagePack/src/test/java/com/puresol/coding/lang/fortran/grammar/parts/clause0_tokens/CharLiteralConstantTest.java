package com.puresol.coding.lang.fortran.grammar.parts.clause0_tokens;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.utils.PersistenceException;

public class CharLiteralConstantTest {

	@Test
	public void test() {
		TokenDefinition definition;
		try {
			definition = FortranGrammar.getInstance().getGrammar()
					.getTokenDefinitions()
					.getDefinition("CHAR_LITERAL_CONSTANT");
			assertTrue(definition.getPattern().matcher("'String'").find());
			assertTrue(definition.getPattern().matcher("\"String\"").find());
			assertFalse(definition.getPattern().matcher("\"String").find());
			assertFalse(definition.getPattern().matcher("'String").find());
			assertFalse(definition.getPattern()
					.matcher("'X-COORDINATES IN THE HEXAGONAL CASSETTE ")
					.find());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
