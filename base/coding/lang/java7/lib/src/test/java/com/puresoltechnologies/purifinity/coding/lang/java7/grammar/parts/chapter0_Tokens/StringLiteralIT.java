package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammar;
import com.puresoltechnologies.purifinity.uhura.lexer.Lexer;
import com.puresoltechnologies.purifinity.uhura.lexer.TokenStream;
import com.puresoltechnologies.purifinity.uhura.source.FixedCodeLocation;

public class StringLiteralIT {

    @Test
    public void test() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();
	TokenStream tokenStream = lexer.lex(new FixedCodeLocation("\"String\"")
		.loadSourceCode());
	assertEquals(1, tokenStream.size());
	assertEquals("StringLiteral", tokenStream.get(0).getName());
    }

    @Test
    public void test2() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();
	TokenStream tokenStream = lexer.lex(new FixedCodeLocation(
		"\"Test \\\"String\\\" Test\"").loadSourceCode());
	assertEquals(1, tokenStream.size());
	assertEquals("StringLiteral", tokenStream.get(0).getName());
    }

    @Test
    public void test3() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();
	TokenStream tokenStream = lexer.lex(new FixedCodeLocation(
		"\"Test \\\"String\\\" Test\" \"2. String\"").loadSourceCode());
	assertEquals(3, tokenStream.size());
	assertEquals("StringLiteral", tokenStream.get(0).getName());
	assertEquals("WhiteSpace", tokenStream.get(1).getName());
	assertEquals("StringLiteral", tokenStream.get(2).getName());
    }
}
