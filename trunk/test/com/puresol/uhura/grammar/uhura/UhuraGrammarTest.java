package com.puresol.uhura.grammar.uhura;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;

import junit.framework.TestCase;

public class UhuraGrammarTest extends TestCase {

	@Test
	public void test() {
		Grammar grammar = UhuraGrammar.getGrammar();
		grammar.printProductions();
	}
}
