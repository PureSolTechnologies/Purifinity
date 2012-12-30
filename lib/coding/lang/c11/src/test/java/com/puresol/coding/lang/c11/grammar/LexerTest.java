package com.puresol.coding.lang.c11.grammar;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.source.BuiltinSource;

public class LexerTest {

    private static Lexer lexer;

    @BeforeClass
    public static void initialize() throws Exception {
	Grammar grammar = C11Grammar.getGrammar();
	lexer = grammar.createLexer();
    }

    @Test
    public void test() throws Exception {
	lexer.lex(new BuiltinSource("1.234E+56").load());
    }

}
