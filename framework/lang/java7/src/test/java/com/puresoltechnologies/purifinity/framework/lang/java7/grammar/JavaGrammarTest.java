package com.puresoltechnologies.purifinity.framework.lang.java7.grammar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.grammar.GrammarReader;
import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammar;

public class JavaGrammarTest {

	@BeforeClass
	public static void initialize() {
		URL url = JavaGrammar.class.getResource(JavaGrammar.GRAMMAR_RESOURCE);
		assertNotNull("Grammar resource was not found.", url);
	}

	@Test
	public void testReadGrammar() {
		try {
			InputStream inputStream = JavaGrammar.class
					.getResourceAsStream(JavaGrammar.GRAMMAR_RESOURCE);
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
		Grammar grammar = JavaGrammar.getInstance();
		assertNotNull(grammar);
		assertSame(grammar, JavaGrammar.getInstance());
	}

	@Test
	public void testPrint() {
		try {
			Grammar grammar = JavaGrammar.getInstance();
			System.out.println(grammar);
		} catch (Throwable e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
