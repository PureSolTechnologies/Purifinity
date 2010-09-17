package com.puresol.uhura.parser.lr;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.Terminal;

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
			LR0ParserTable table = new LR0ParserTable(grammar);
			System.out.println(table.toString());
			System.out
					.println(table.getAction(5, new Terminal("b")).toString());
			fail("An GrammarException was expected due to the fact that this grammar is not LR(0)!");
		} catch (GrammarException e) {
		}
	}

	@Test
	public void test2() {
		try {
			Grammar grammar = TestGrammars.getTestGrammarFromDragonBook();
			LR0ParserTable table = new LR0ParserTable(grammar);
			System.out.println(table.toString());
			System.out.println(table.getAction(2, new Terminal("STAR"))
					.toString());
			fail("An GrammarException was expected due to the fact that this grammar is not LR(0)!");
		} catch (GrammarException e) {
		}
	}

}
