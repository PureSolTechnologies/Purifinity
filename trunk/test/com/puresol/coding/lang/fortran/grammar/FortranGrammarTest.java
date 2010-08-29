package com.puresol.coding.lang.fortran.grammar;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;

public class FortranGrammarTest extends TestCase {

	@Test
	public void testSingleton() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		Grammar grammar = FortranGrammar.get();
		assertNotNull(grammar);
		assertSame(grammar, FortranGrammar.get());
	}

	@Test
	public void testPrint() {
		Grammar grammar = FortranGrammar.get();
		System.out.println(grammar);
	}
}
