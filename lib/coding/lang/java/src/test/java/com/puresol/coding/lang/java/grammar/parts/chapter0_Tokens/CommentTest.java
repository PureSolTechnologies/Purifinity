package com.puresol.coding.lang.java.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.source.BuiltinSource;

public class CommentTest {

    @Test
    public void testTraditionalComment() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();

	TokenStream tokenStream = lexer.lex(new BuiltinSource(
		"/* This is a traditional comment... */").load());
	assertEquals(1, tokenStream.size());
	assertEquals(Visibility.IGNORED, tokenStream.get(0).getVisibility());
    }

    @Test
    public void testTraditionalComment2() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();

	TokenStream tokenStream = lexer.lex(new BuiltinSource(
		"/* to select the \"client\" VM */").load());
	assertEquals(1, tokenStream.size());
	assertEquals(Visibility.IGNORED, tokenStream.get(0).getVisibility());
    }

    @Test
    public void testTraditionalComment3() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();

	TokenStream tokenStream = lexer
		.lex(new BuiltinSource(
			"/*\n"
				+ "* @(#)Activation.java  1.78 10/03/23\n"
				+ "*\n"
				+ "* Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.\n"
				+ "* ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.\n"
				+ "*/").load());
	assertEquals(1, tokenStream.size());
	assertEquals(Visibility.IGNORED, tokenStream.get(0).getVisibility());
    }
}
