package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.parsers.lexer.Lexer;
import com.puresoltechnologies.parsers.lexer.LexerException;
import com.puresoltechnologies.parsers.lexer.RegExpLexer;
import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.parsers.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammar;

public class FortranLexerTest {

	@Test
	public void testRealLiteral() {
		try {
			Grammar grammar = FortranGrammar.getInstance();
			assertNotNull(grammar);
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer.lex(new FixedCodeLocation(
					"2.0 - 3.0").getSourceCode());
			assertEquals(5, tokenStream.size());
			assertEquals("REAL_LITERAL_CONSTANT", tokenStream.get(0).getName());
			assertEquals("WHITESPACE", tokenStream.get(1).getName());
			assertEquals("MINUS", tokenStream.get(2).getName());
			assertEquals("WHITESPACE", tokenStream.get(3).getName());
			assertEquals("REAL_LITERAL_CONSTANT", tokenStream.get(4).getName());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testRealLiteral2() {
		try {
			Grammar grammar = FortranGrammar.getInstance();
			assertNotNull(grammar);
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer.lex(new FixedCodeLocation("3.OR.4")
					.getSourceCode());
			assertEquals(3, tokenStream.size());
			assertEquals("INT_LITERAL_CONSTANT", tokenStream.get(0).getName());
			assertEquals("OR", tokenStream.get(1).getName());
			assertEquals("INT_LITERAL_CONSTANT", tokenStream.get(2).getName());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
