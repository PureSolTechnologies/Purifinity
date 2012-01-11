package com.puresol.coding.lang.java.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.utils.PersistenceException;

public class CommentTest {

	@Test
	public void testTraditionalComment() {
		try {
			Lexer lexer = JavaGrammar.getInstance().getLexer();

			TokenStream tokenStream = lexer.lex(new StringReader(
					"/* This is a traditional comment... */"), "SampleString");
			assertEquals(0, tokenStream.size());

			tokenStream = lexer.lex(new StringReader(
					"/* to select the \"client\" VM */"), "SampleString");
			assertEquals(0, tokenStream.size());

			tokenStream = lexer
					.lex(new StringReader(
							"/*\n"
									+ "* @(#)Activation.java  1.78 10/03/23\n"
									+ "*\n"
									+ "* Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.\n"
									+ "* ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.\n"
									+ "*/"), "SampleString");
			assertEquals(0, tokenStream.size());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
