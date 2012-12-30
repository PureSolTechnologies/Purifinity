package com.puresol.coding.lang.java.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.source.BuiltinSource;

public class FloatingPointLiteralTest {

    @Test
    public void test() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();
	LexerResult lexerResult = lexer.lex(new BuiltinSource("0.1234").load());
	TokenStream tokenStream = lexerResult.getTokenStream();
	assertEquals(1, tokenStream.size());
	assertEquals("FloatingPointLiteral", tokenStream.get(0).getName());
    }

}
