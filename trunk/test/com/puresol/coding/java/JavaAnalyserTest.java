/***************************************************************************
 *
 *   JavaAnalyserTest.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.java;

import java.io.File;
import java.util.Hashtable;

import org.junit.Test;

import com.puresol.coding.Analyser;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaAnalyserTest extends TestCase {

	@Test
	public void testGetLexerAndParserTokens() {
		Analyser analyser = new JavaAnalyser(new File("."), new File(
				"test/com/puresol/coding/java/JavaAnalyserTest.java"));
		Hashtable<Integer, String> tokens = analyser.getLexerTokens();
		Assert.assertNotNull(tokens);
		Assert.assertTrue(tokens.size() > 0);

		tokens = analyser.getParserTokens();
		Assert.assertNotNull(tokens);
		Assert.assertTrue(tokens.size() > 0);
	}

	@Test
	public void testIsSuitable() {
		Assert.assertTrue(JavaAnalyser.isSuitable(new File("Java.java")));
		Assert.assertFalse(JavaAnalyser.isSuitable(new File("NotJava.f")));
	}
}
