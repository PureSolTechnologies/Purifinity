package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.lexer.TokenStream;
import com.puresoltechnologies.parsers.impl.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammar;

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
