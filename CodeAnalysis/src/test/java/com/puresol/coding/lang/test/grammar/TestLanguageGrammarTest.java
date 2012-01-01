package com.puresol.coding.lang.test.grammar;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;
import com.puresol.utils.PersistenceException;

public class TestLanguageGrammarTest {

	@Test
	public void testGetGrammarStream() {
		assertNotNull(TestLanguageGrammar.class
				.getResource(TestLanguageGrammar.GRAMMAR_RESOURCE));
	}

	@Test
	public void testReadGrammar() {
		try {
			InputStream inputStream = TestLanguageGrammar.class
					.getResourceAsStream(TestLanguageGrammar.GRAMMAR_RESOURCE);
			try {
				new GrammarReader(inputStream);
			} finally {
				inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testSingleton() {
		try {
			Logger.getRootLogger().setLevel(Level.DEBUG);
			Grammar grammar = TestLanguageGrammar.getInstance().getGrammar();
			assertNotNull(grammar);
			assertSame(grammar, TestLanguageGrammar.getInstance().getGrammar());
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testPrint() {
		try {
			Grammar grammar = TestLanguageGrammar.getInstance().getGrammar();
			System.out.println(grammar);
		} catch (Throwable e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
