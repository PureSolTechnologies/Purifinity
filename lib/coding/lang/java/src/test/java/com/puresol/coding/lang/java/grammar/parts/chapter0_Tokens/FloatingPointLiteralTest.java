package com.puresol.coding.lang.java.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.source.FixedCodeLocation;

public class FloatingPointLiteralTest {

    @Test
    public void test() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();
	TokenStream tokenStream = lexer.lex(new FixedCodeLocation("0.1234").load());
	assertEquals(1, tokenStream.size());
	assertEquals("FloatingPointLiteral", tokenStream.get(0).getName());
    }

}
