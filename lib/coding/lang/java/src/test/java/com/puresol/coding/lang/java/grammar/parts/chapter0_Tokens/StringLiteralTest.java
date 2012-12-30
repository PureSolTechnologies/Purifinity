package com.puresol.coding.lang.java.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.source.BuiltinSource;

public class StringLiteralTest {

    @Test
    public void test() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();
	TokenStream tokenStream = lexer.lex(new BuiltinSource("\"String\"")
		.load());
	assertEquals(1, tokenStream.size());
	assertEquals("StringLiteral", tokenStream.get(0).getName());
    }

    @Test
    public void test2() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();
	TokenStream tokenStream = lexer.lex(new BuiltinSource(
		"\"Test \\\"String\\\" Test\"").load());
	assertEquals(1, tokenStream.size());
	assertEquals("StringLiteral", tokenStream.get(0).getName());
    }

    @Test
    public void test3() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();
	TokenStream tokenStream = lexer.lex(new BuiltinSource(
		"\"Test \\\"String\\\" Test\" \"2. String\"").load());
	assertEquals(3, tokenStream.size());
	assertEquals("StringLiteral", tokenStream.get(0).getName());
	assertEquals("WhiteSpace", tokenStream.get(1).getName());
	assertEquals("StringLiteral", tokenStream.get(2).getName());
    }
}
