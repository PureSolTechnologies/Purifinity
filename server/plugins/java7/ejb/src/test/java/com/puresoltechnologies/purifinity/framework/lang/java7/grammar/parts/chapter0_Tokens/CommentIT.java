package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.parsers.grammar.token.Visibility;
import com.puresoltechnologies.parsers.lexer.Lexer;
import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.parsers.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.JavaGrammar;

public class CommentIT {

	@Test
	public void testTraditionalComment() throws Exception {
		Lexer lexer = JavaGrammar.getInstance().getLexer();

		TokenStream tokenStream = lexer.lex(new FixedCodeLocation(
				"/* This is a traditional comment... */").getSourceCode());
		assertEquals(1, tokenStream.size());
		assertEquals(Visibility.IGNORED, tokenStream.get(0).getVisibility());
	}

	@Test
	public void testTraditionalComment2() throws Exception {
		Lexer lexer = JavaGrammar.getInstance().getLexer();

		TokenStream tokenStream = lexer.lex(new FixedCodeLocation(
				"/* to select the \"client\" VM */").getSourceCode());
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
								+ "*/").getSourceCode());
		assertEquals(1, tokenStream.size());
		assertEquals(Visibility.IGNORED, tokenStream.get(0).getVisibility());
	}
}
