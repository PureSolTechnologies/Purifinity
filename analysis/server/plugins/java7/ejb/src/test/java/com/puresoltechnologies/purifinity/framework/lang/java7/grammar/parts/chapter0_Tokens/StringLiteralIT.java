package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.parsers.lexer.Lexer;
import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.parsers.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.JavaGrammar;

public class StringLiteralIT {

	@Test
	public void test() throws Exception {
		Lexer lexer = JavaGrammar.getInstance().getLexer();
		TokenStream tokenStream = lexer.lex(new FixedCodeLocation("\"String\"")
				.getSourceCode());
		assertEquals(1, tokenStream.size());
		assertEquals("StringLiteral", tokenStream.get(0).getName());
	}

	@Test
	public void test2() throws Exception {
		Lexer lexer = JavaGrammar.getInstance().getLexer();
		TokenStream tokenStream = lexer.lex(new FixedCodeLocation(
				"\"Test \\\"String\\\" Test\"").getSourceCode());
		assertEquals(1, tokenStream.size());
		assertEquals("StringLiteral", tokenStream.get(0).getName());
	}

	@Test
	public void test3() throws Exception {
		Lexer lexer = JavaGrammar.getInstance().getLexer();
		TokenStream tokenStream = lexer.lex(new FixedCodeLocation(
				"\"Test \\\"String\\\" Test\" \"2. String\"").getSourceCode());
		assertEquals(3, tokenStream.size());
		assertEquals("StringLiteral", tokenStream.get(0).getName());
		assertEquals("WhiteSpace", tokenStream.get(1).getName());
		assertEquals("StringLiteral", tokenStream.get(2).getName());
	}
}
