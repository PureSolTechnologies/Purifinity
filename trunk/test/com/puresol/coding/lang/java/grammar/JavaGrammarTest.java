package com.puresol.coding.lang.java.grammar;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;

public class JavaGrammarTest extends TestCase {

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
