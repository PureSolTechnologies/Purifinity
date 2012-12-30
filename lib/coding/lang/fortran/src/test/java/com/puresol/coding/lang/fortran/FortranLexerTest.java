package com.puresol.coding.lang.fortran;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.source.BuiltinSource;

public class FortranLexerTest {

    @Test
    public void testRealLiteral() {
	try {
	    Grammar grammar = FortranGrammar.getInstance();
	    assertNotNull(grammar);
	    Lexer lexer = new RegExpLexer(grammar);
	    TokenStream tokenStream = lexer.lex(new BuiltinSource("2.0 - 3.0")
		    .load());
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
	    TokenStream tokenStream = lexer.lex(new BuiltinSource("3.OR.4").load());
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
