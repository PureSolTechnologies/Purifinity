package com.puresoltechnologies.purifinity.framework.lang.c11.grammar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.token.TokenDefinition;
import com.puresoltechnologies.parsers.impl.grammar.token.TokenDefinitionSet;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.framework.lang.c11.grammar.C11Grammar;

public class LexerTest {

    private static Grammar grammar;
    private static Lexer lexer;

    @BeforeClass
    public static void initialize() throws Exception {
	grammar = C11Grammar.getGrammar();
	lexer = grammar.createLexer(LexerTest.class.getClassLoader());
    }

    @Test
    public void testFloat() throws Exception {
	lexer.lex(new FixedCodeLocation("1.234E+56").loadSourceCode());
	lexer.lex(new FixedCodeLocation("1234.E+56").loadSourceCode());
	lexer.lex(new FixedCodeLocation(".1234E+56").loadSourceCode());
    }

    @Test
    public void testStringLiteralEmpty() {
	TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
	TokenDefinition definition = tokenDefinitions
		.getDefinition("string-literal");
	assertTrue(definition.getPattern().matcher("\"\"").matches());
	assertFalse(definition.getPattern().matcher("\"\n\"").matches());
	assertTrue(definition.getPattern().matcher("\"\\n\"").matches());
	assertFalse(definition.getPattern().matcher("\"\"\"").matches());
    }

    @Test
    public void testStringLiteralNewLines() {
	TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
	TokenDefinition definition = tokenDefinitions
		.getDefinition("string-literal");
	assertFalse(definition.getPattern().matcher("\"\n\"").matches());
	assertTrue(definition.getPattern().matcher("\"\\n\"").matches());
	assertFalse(definition.getPattern().matcher("\"\"\"").matches());
    }

    @Test
    public void testStringLiteralQuotes() {
	TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
	TokenDefinition definition = tokenDefinitions
		.getDefinition("string-literal");
	assertFalse(definition.getPattern().matcher("\"\"\"").matches());
	assertTrue(definition.getPattern().matcher("\"\\\"\"").matches());
    }

    @Test
    public void testStringLiteralBackSlash() {
	TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
	TokenDefinition definition = tokenDefinitions
		.getDefinition("string-literal");
	assertFalse(definition.getPattern().matcher("\"\\\"").matches());
	assertTrue(definition.getPattern().matcher("\"\\\\\"").matches());
    }

    @Test
    public void testHeaderName() {
	TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
	TokenDefinition definition = tokenDefinitions
		.getDefinition("header-name");
	assertTrue(definition.getPattern().matcher("<hallo.h>").matches());
	assertTrue(definition.getPattern().matcher("\"hallo.h\"").matches());
    }
}