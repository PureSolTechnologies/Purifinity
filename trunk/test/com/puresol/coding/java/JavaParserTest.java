package com.puresol.coding.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.puresol.coding.java.JavaKeywords;
import com.puresol.coding.java.JavaSymbols;
import com.puresol.coding.java.parts.JavaFile;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.Lexer;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.Parser;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaParserTest extends TestCase {

	@Test
	public void test() {
		try {
			DefaultPreConditioner conditioner = new DefaultPreConditioner(
					new File("test/com/puresol/coding/java/JavaLexerTest.java"));
			TokenStream tokenStream = conditioner.getTokenStream();
			Lexer lexer = new Lexer(tokenStream);
			lexer.addDefinitions(JavaKeywords.class);
			lexer.addDefinitions(JavaSymbols.class);
			TokenStream tokenStream2 = lexer.getTokenStream();
			Parser parser = new Parser(tokenStream2);
			parser.parse(JavaFile.class);
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
		}
	}
}
