/**
 * Test //
 */
package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

import junit.framework.Assert;
import junit.framework.TestCase;

public class FortranParserTest extends TestCase {

	@Test
	public void test() {
		FortranParser parser = null;
		try {
			FortranPreConditioner conditioner = new FortranPreConditioner(
					new File(
							"test/com/puresol/coding/lang/fortran/samples/zgerc.f"));
			// DefaultPreConditioner conditioner =
			// new DefaultPreConditioner(
			// new File(
			// "test/com/puresol/coding/lang/java/FortranParserTest.java"));
			TokenStream tokenStream = conditioner.getTokenStream();
			FortranLexer lexer = new FortranLexer(tokenStream);
			TokenStream tokenStream2 = lexer.getTokenStream();
			for (Token token : tokenStream2.getTokens()) {
				System.out.println(token.toString());
			}
			parser = new FortranParser(tokenStream2);
			parser.scan();
			for (CodeRange codeRange : parser.getCodeRanges()) {
				System.out.println(codeRange.toString());
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
		} catch (PartDoesNotMatchException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (ParserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public static void main(String args[]) {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		FortranParser parser = null;
		try {
			FortranPreConditioner conditioner = new FortranPreConditioner(
					new File("/home/ludwig/workspace/Dyn3D/src/fort/libapr.f"));
			// DefaultPreConditioner conditioner =
			// new DefaultPreConditioner(
			// new File(
			// "test/com/puresol/coding/lang/java/FortranParserTest.java"));
			TokenStream tokenStream = conditioner.getTokenStream();
			for (Token token : tokenStream.getTokens()) {
				if (token.getPublicity() == TokenPublicity.VISIBLE) {
					System.out.println(token.toString());
				}
			}
			FortranLexer lexer = new FortranLexer(tokenStream);
			TokenStream tokenStream2 = lexer.getTokenStream();
			for (Token token : tokenStream2.getTokens()) {
				if (token.getPublicity() == TokenPublicity.VISIBLE) {
					System.out.println(token.toString());
				}
			}
			parser = new FortranParser(tokenStream2);
			parser.scan();
			for (CodeRange codeRange : parser.getCodeRanges()) {
				System.out.println(codeRange.toString());
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
		} catch (PartDoesNotMatchException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (ParserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
