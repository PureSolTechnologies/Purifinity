package com.puresol.uhura.grammar;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.puresol.uhura.parser.ParserTree;

public class GrammarFileTest {

	private InputStream inputStream = null;

	@Before
	@Test
	public void testInputStreamAndSetup() {
		inputStream = getClass().getResourceAsStream(
				"/com/puresol/uhura/grammar/TestGrammar.g");
		assertNotNull(inputStream);
	}

	@Test
	public void testRead() {
		try {
			assertNotNull(inputStream);
			GrammarFile file = new GrammarFile(inputStream);
			ParserTree ast = file.getAST();
			assertNotNull(ast);
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@After
	public void teardown() {
		try {
			assertNotNull(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
