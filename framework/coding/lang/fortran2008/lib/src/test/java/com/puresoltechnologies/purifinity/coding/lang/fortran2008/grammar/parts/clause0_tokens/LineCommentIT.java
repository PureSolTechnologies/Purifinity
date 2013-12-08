package com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.parts.clause0_tokens;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

import com.puresoltechnologies.parser.impl.grammar.token.TokenDefinition;
import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammar;

public class LineCommentIT {

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
	definition = FortranGrammar.getInstance().getTokenDefinitions()
		.getDefinition("LINE_COMMENT");
	assertTrue(definition.getPattern()
		.matcher("! = IBKTYP_inp if 4/5/-5; =0 otherwise\n").matches());
    }

}
