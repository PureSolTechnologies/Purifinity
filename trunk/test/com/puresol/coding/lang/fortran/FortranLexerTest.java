package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.SourceCodeLexer;
import com.puresol.coding.lang.fortran.evaluator.GotoEvaluator;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class FortranLexerTest {

	@Test
	public void testLexer() {
		try {
			FortranPreConditioner conditioner = new FortranPreConditioner(
					new File(
							"test/com/puresol/coding/lang/fortran/samples/zgerc.f"));
			TokenStream tokenStream = conditioner.getTokenStream();
			SourceCodeLexer lexer = new SourceCodeLexer(Fortran.getInstance(),
					tokenStream);
			TokenStream tokenStream2 = lexer.getTokenStream();
			for (Token token : tokenStream2.getTokens()) {
				System.out.println(token.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (NoMatchingTokenDefinitionFound e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	public static void main(String args[]) {
		try {
			ProjectAnalyzer projectAnalyser = ProjectAnalyzer.create(new File(
					"fort/dyn3d_3.f"), new File("../Dyn3D/src"));
			projectAnalyser.run();
			Assert.assertTrue(projectAnalyser.getFiles().size() > 0);
			for (File file : projectAnalyser.getFiles()) {
				System.out.println(file + "\n");
			}
			Analyzer analyser = projectAnalyser.getAnalyzer(new File(
					"/fort/dyn3d_3.f"));
			TokenStream tokenStream = analyser.getRootCodeRange()
					.getTokenStream();
			SourceCodeLexer lexer = new SourceCodeLexer(Fortran.getInstance(),
					tokenStream);
			TokenStream tokenStream2 = lexer.getTokenStream();
			for (Token token : tokenStream2.getTokens()) {
				System.out.println(token.toString());
			}
			GotoEvaluator evaluator = new GotoEvaluator(projectAnalyser);
			evaluator.run();
			Assert.assertTrue(evaluator.getGotoNum() > 0);
		} catch (NoMatchingTokenDefinitionFound e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
