package com.puresol.coding.lang.fortran.grammar.parts.clause0_tokens;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.token.TokenDefinition;

public class CharLiteralConstantTest {

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
