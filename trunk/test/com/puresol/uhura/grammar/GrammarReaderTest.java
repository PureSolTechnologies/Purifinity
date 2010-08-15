package com.puresol.uhura.grammar;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.ast.ASTPrinter;
import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.parser.ParserException;

import junit.framework.TestCase;

public class GrammarReaderTest extends TestCase {

	@Test
	public void testRead() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		try {
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestGrammar.g"));
			assertTrue(reader.call());
			SyntaxTree ast = reader.getSyntaxTree();
			ASTPrinter printer = new ASTPrinter(System.out);
			printer.println(ast);
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (ParserException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
