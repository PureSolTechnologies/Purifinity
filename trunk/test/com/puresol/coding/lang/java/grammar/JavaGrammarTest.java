package com.puresol.coding.lang.java.grammar;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;

public class JavaGrammarTest {

	@Test
	public void testGetGrammarStream() {
		assertNotNull(JavaGrammar.class.getResource(JavaGrammar.GRAMMAR_RESOURCE));
	}

	@Test
	public void testReadGrammar() {
		InputStream inputStream = null;
		try {
			inputStream = JavaGrammar.class
					.getResourceAsStream(JavaGrammar.GRAMMAR_RESOURCE);
			new GrammarReader(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// nothing to catch here...
				}
			}
		}
	}

	@Test
	public void testSingleton() {
		try {
			Logger.getRootLogger().setLevel(Level.DEBUG);
			Grammar grammar = JavaGrammar.getInstance().getGrammar();
			assertNotNull(grammar);
			assertSame(grammar, JavaGrammar.getInstance().getGrammar());
		} catch (Throwable e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testPrint() {
		try {
			Grammar grammar = JavaGrammar.getInstance().getGrammar();
			System.out.println(grammar);
		} catch (Throwable e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
