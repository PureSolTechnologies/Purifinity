/**
 * Test //
 */
package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.coding.analysis.SourceCodeLexer;
import com.puresol.coding.lang.java.JavaParser;
import com.puresol.coding.lang.java.samples.ProgrammingLanguage;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.lexer.LexerException;
import com.puresol.parser.lexer.NoMatchingTokenDefinitionFound;
import com.puresol.parser.preconditioner.DefaultPreConditioner;
import com.puresol.parser.tokens.TokenStream;
import com.puresol.utils.FileUtilities;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaParserTest extends TestCase {

	public int testInt;

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		JavaParser parser = null;
		try {
			DefaultPreConditioner conditioner = new DefaultPreConditioner(
					new File(new File("test"), FileUtilities
							.classToRelativePackagePath(
									ProgrammingLanguage.class).toString()));
			TokenStream tokenStream = conditioner.getTokenStream();
			SourceCodeLexer lexer = new SourceCodeLexer(Java.getInstance(),
					tokenStream);
			TokenStream tokenStream2 = lexer.getTokenStream();
			parser = (JavaParser) AbstractAnalyser.createParserInstance(
					JavaParser.class, tokenStream2);
			parser.scan();
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
		} catch (ParserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
