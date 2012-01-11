package com.puresol.coding.lang.java.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.utils.PersistenceException;

public class StringLiteralTest {

	@Test
	public void test() {
		try {
			Lexer lexer = JavaGrammar.getInstance().getLexer();
			TokenStream tokenStream = lexer.lex(new StringReader("\"String\""),
					"SampleString");
			assertEquals(1, tokenStream.size());
			assertEquals("StringLiteral", tokenStream.get(0).getName());

			tokenStream = lexer.lex(new StringReader(
					"\"Test \\\"String\\\" Test\""), "SampleString");
			assertEquals(1, tokenStream.size());
			assertEquals("StringLiteral", tokenStream.get(0).getName());

			tokenStream = lexer.lex(new StringReader(
					"\"Test \\\"String\\\" Test\" \"2. String\""),
					"SampleString");
			assertEquals(2, tokenStream.size());
			assertEquals("StringLiteral", tokenStream.get(0).getName());
			assertEquals("StringLiteral", tokenStream.get(1).getName());

		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}