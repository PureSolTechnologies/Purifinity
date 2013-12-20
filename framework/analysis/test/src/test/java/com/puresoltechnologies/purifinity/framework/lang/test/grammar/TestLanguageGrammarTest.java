package com.puresoltechnologies.purifinity.framework.lang.test.grammar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.grammar.GrammarReader;
import com.puresoltechnologies.purifinity.framework.lang.test.grammar.TestLanguageGrammar;

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
		Grammar grammar = TestLanguageGrammar.getInstance();
		assertNotNull(grammar);
		assertSame(grammar, TestLanguageGrammar.getInstance());
	}

	@Test
	public void testPrint() {
		try {
			Grammar grammar = TestLanguageGrammar.getInstance();
			System.out.println(grammar);
		} catch (Throwable e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
