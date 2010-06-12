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

import com.puresol.coding.SourceCodeLexer;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.di.DIClassBuilder;
import com.puresol.utils.di.Injection;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ProgramTest extends TestCase {

	@Test
	public void test() {
		Program program = null;
		try {
			FortranPreConditioner conditioner = new FortranPreConditioner(
					new File("test"), new File(
							"com/puresol/coding/lang/fortran/samples/zgerc.f"));
			// DefaultPreConditioner conditioner =
			// new DefaultPreConditioner(
			// new File(
			// "test/com/puresol/coding/lang/java/FortranParserTest.java"));
			TokenStream tokenStream = conditioner.getTokenStream();
			SourceCodeLexer lexer = new SourceCodeLexer(Fortran.getInstance(),
					tokenStream);
			TokenStream tokenStream2 = lexer.getTokenStream();
			for (Token token : tokenStream2.getTokens()) {
				System.out.println(token.toString());
			}
			program = DIClassBuilder.forInjections(
					Injection.named("TokenStream", tokenStream2))
					.createInstance(Program.class);
			program.scan();
			for (CodeRange codeRange : program.getChildCodeRanges()) {
				System.out.println(codeRange.toString());
				Assert.assertEquals(new File(
						"com/puresol/coding/lang/fortran/samples/zgerc.f"),
						codeRange.getFile());
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
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public static void main(String args[]) {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		Program program = null;
		try {
			FortranPreConditioner conditioner = new FortranPreConditioner(
					new File("/home/ludwig/workspace/Dyn3D/src"), new File(
							"/fort/libapr.f"));
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
			SourceCodeLexer lexer = new SourceCodeLexer(Fortran.getInstance(),
					tokenStream);
			TokenStream tokenStream2 = lexer.getTokenStream();
			for (Token token : tokenStream2.getTokens()) {
				if (token.getPublicity() == TokenPublicity.VISIBLE) {
					System.out.println(token.toString());
				}
			}
			program = DIClassBuilder.forInjections(
					Injection.unnamed(tokenStream2)).createInstance(
					Program.class);
			program.scan();
			for (CodeRange codeRange : program.getChildCodeRanges()) {
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
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
