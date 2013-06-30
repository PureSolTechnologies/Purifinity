package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammar;
import com.puresol.purifinity.uhura.grammar.token.Visibility;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.source.FixedCodeLocation;

public class CommentIT {

    @Test
    public void testTraditionalComment() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();

	TokenStream tokenStream = lexer.lex(new FixedCodeLocation(
		"/* This is a traditional comment... */").loadSourceCode());
	assertEquals(1, tokenStream.size());
	assertEquals(Visibility.IGNORED, tokenStream.get(0).getVisibility());
    }

    @Test
    public void testTraditionalComment2() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();

	TokenStream tokenStream = lexer.lex(new FixedCodeLocation(
		"/* to select the \"client\" VM */").loadSourceCode());
	assertEquals(1, tokenStream.size());
	assertEquals(Visibility.IGNORED, tokenStream.get(0).getVisibility());
    }

    @Test
    public void testTraditionalComment3() throws Exception {
	Lexer lexer = JavaGrammar.getInstance().getLexer();

	TokenStream tokenStream = lexer
		.lex(new FixedCodeLocation(
			"/*\n"
				+ "* @(#)Activation.java  1.78 10/03/23\n"
				+ "*\n"
				+ "* Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.\n"
				+ "* ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.\n"
				+ "*/").loadSourceCode());
	assertEquals(1, tokenStream.size());
	assertEquals(Visibility.IGNORED, tokenStream.get(0).getVisibility());
    }
}
