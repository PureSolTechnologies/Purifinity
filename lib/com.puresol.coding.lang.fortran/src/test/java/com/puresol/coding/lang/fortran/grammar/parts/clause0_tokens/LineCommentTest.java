package com.puresol.coding.lang.fortran.grammar.parts.clause0_tokens;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.utils.PersistenceException;

public class LineCommentTest {

	@Test
	public void testRegExp() {
		assertTrue(Pattern.matches("^!", "!"));

		assertTrue(Pattern.matches("^![^\\r\\n]", "!x"));
		assertFalse(Pattern.matches("^![^\\r\\n]", "!\n"));

		assertTrue(Pattern.matches("^![^\\r\\n]*", "!xxxx"));
		assertFalse(Pattern.matches("^![^\\r\\n]*", "!x\n"));

		assertTrue(Pattern.matches("^![^\\r\\n]*\n", "!xxxx\n"));

		assertTrue(Pattern.matches("![^\\n\\\r]*(\\n|\\r\n|\\r)", "!xxxx\n"));
		assertTrue(Pattern.matches("![^\\n\\\r]*(\\n|\\r\n|\\r)",
				"! = IBKTYP_inp if 4/5/-5; =0 otherwise\n"));
	}

	@Test
	public void test() {
		TokenDefinition definition;
		try {
			definition = FortranGrammar.getInstance().getGrammar()
					.getTokenDefinitions().getDefinition("LINE_COMMENT");
			assertTrue(definition.getPattern()
					.matcher("! = IBKTYP_inp if 4/5/-5; =0 otherwise\n")
					.matches());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
