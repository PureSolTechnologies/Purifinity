package com.puresol.uhura.parser.lr;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.parser.ParserException;

/**
 * This test should the the LR(0) capability of the LR0ParserTable, but there
 * was no valid LR(0) grammar for testing purposes available, yet. Therefore, we
 * only test the error behavior for non LR(0) grammars.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LR0ParserTableTest extends TestCase {

	@Test
	public void test() {
		try {
			Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
			new LR0ParserTable(grammar);
			fail("An ParserException was expected due to the fact that this grammar is not LR(0)!");
		} catch (ParserException e) {
		}
	}

	@Test
	public void test2() {
		try {
			Grammar grammar = TestGrammars.getTestGrammarFromDragonBook();
			new LR0ParserTable(grammar);
			fail("An ParserException was expected due to the fact that this grammar is not LR(0)!");
		} catch (ParserException e) {
		}
	}

}
