package com.puresoltechnologies.parser.impl.grammar;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresoltechnologies.parser.impl.grammar.TestGrammars;

public class TestGrammarsTest {

	@Test
	public void testGrammarFromLR1Pamphlet() {
		assertNotNull(TestGrammars.getGrammarFromLRkPamphlet());
	}

	@Test
	public void testLALR1TestGrammarFromDragonBook() {
		assertNotNull(TestGrammars.getLALR1TestGrammarFromDragonBook());
	}

	@Test
	public void testLLGrammarFromDragonBook() {
		assertNotNull(TestGrammars.getLLGrammarFromDragonBook());
	}

	@Test
	public void testLR1TestGrammarFromDragonBook() {
		assertNotNull(TestGrammars.getLR1TestGrammarFromDragonBook());
	}

	@Test
	public void testgetSLR1TestGrammarFromDragonBook() {
		assertNotNull(TestGrammars.getSLR1TestGrammarFromDragonBook());
	}

}
