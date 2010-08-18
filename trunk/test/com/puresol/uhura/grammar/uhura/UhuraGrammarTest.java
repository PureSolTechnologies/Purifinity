package com.puresol.uhura.grammar.uhura;

import org.junit.Test;

import com.puresol.uhura.grammar.GrammarException;

import junit.framework.TestCase;

public class UhuraGrammarTest extends TestCase {

	@Test
	public void test() {
		try {
			UhuraGrammar grammar = new UhuraGrammar();
			grammar.printProductions();
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
