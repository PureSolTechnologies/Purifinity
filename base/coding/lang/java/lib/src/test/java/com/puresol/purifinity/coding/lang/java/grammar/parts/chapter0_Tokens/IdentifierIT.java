package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammar;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.source.FixedCodeLocation;

public class IdentifierIT {

    @Test
    public void testIdentifier() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();
	TokenStream tokenStream = lexer.lex(new FixedCodeLocation("identifier")
		.loadSourceCode());
	assertEquals(1, tokenStream.size());
	assertEquals("Identifier", tokenStream.get(0).getName());
    }

}
