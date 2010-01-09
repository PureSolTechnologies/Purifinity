/***************************************************************************
 *
 *   FortranAnalyserTest.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.fortran;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.junit.Test;

import com.puresol.coding.Analyser;
import com.puresol.coding.CodeRange;

import junit.framework.Assert;
import junit.framework.TestCase;

public class FortranAnalyserTest extends TestCase {

	@Test
	public void testGetLexerAndParserTokens() {
		Analyser analyser = new FortranAnalyser(new File("."), new File(
				"test/com/puresol/coding/fortran/FortranTest.f"));
		Hashtable<Integer, String> tokens = analyser.getLexerTokens();
		Assert.assertNotNull(tokens);
		Assert.assertTrue(tokens.size() > 0);

		tokens = analyser.getParserTokens();
		Assert.assertNotNull(tokens);
		Assert.assertTrue(tokens.size() > 0);
	}

	@Test
	public void testIsSuitable() {
		Assert.assertTrue(FortranAnalyser.isSuitable(new File("Fortran.f")));
		Assert.assertTrue(FortranAnalyser.isSuitable(new File("Fortran.f77")));
		Assert.assertTrue(FortranAnalyser.isSuitable(new File("Fortran.f90")));
		Assert.assertTrue(FortranAnalyser.isSuitable(new File("Fortran.f95")));
		Assert.assertFalse(FortranAnalyser.isSuitable(new File(
				"NotFortran.java")));
	}

	@Test
	public void test() {
		Analyser analyser = new FortranAnalyser(new File("."), new File(
				"test/com/puresol/coding/fortran/FortranTest.f"));
		ArrayList<CodeRange> ranges = analyser.getCodeRanges();
		TokenStream stream = ranges.get(0).getTokenStream();
		Hashtable<Integer, String> tokens = analyser.getLexerTokens();
		for (int index = 0; index < stream.size(); index++) {
			Token token = stream.get(index);
			if (token.getChannel() == Token.HIDDEN_CHANNEL) {
				continue;
			}
			System.out.print("'");
			System.out.print(token.getText());
			System.out.print("'");
			System.out.println("\t(" + token.getLine() + " / "
					+ token.getType() + " / " + tokens.get(token.getType())
					+ ")");
		}
	}
}
