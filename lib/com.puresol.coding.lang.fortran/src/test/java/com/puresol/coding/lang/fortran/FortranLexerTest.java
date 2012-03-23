package com.puresol.coding.lang.fortran;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.SourceCode;
import com.puresol.uhura.lexer.TokenStream;

public class FortranLexerTest {

    @Test
    public void testRealLiteral() {
	try {
	    Grammar grammar = FortranGrammar.getInstance();
	    assertNotNull(grammar);
	    Lexer lexer = new RegExpLexer(grammar);
	    LexerResult result = lexer.lex(SourceCode.read(new StringReader(
		    "2.0 - 3.0"), new File("TEST")), "TEST");
	    TokenStream stream = result.getTokenStream();
	    assertEquals(5, stream.size());
	    assertEquals("REAL_LITERAL_CONSTANT", stream.get(0).getName());
	    assertEquals("WHITESPACE", stream.get(1).getName());
	    assertEquals("MINUS", stream.get(2).getName());
	    assertEquals("WHITESPACE", stream.get(3).getName());
	    assertEquals("REAL_LITERAL_CONSTANT", stream.get(4).getName());
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
	    LexerResult result = lexer.lex(SourceCode.read(new StringReader(
		    "3.OR.4"), new File("TEST")), "TEST");
	    TokenStream stream = result.getTokenStream();
	    assertEquals(3, stream.size());
	    assertEquals("INT_LITERAL_CONSTANT", stream.get(0).getName());
	    assertEquals("OR", stream.get(1).getName());
	    assertEquals("INT_LITERAL_CONSTANT", stream.get(2).getName());
	} catch (IOException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	} catch (LexerException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
    }

}
