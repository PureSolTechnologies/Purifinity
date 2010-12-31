package com.puresol.uhura.parser.lr;

import static org.junit.Assert.*;

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
public class LR0ParserTableTest {

	/**
	 * This test checks the LR1 grammar from the LRk pamphlet for LR0 parser
	 * table generation. Due to the ambiguity of the grammar for LR0 an
	 * exception is expected.
	 * 
	 * @throws GrammarException
	 */
	@Test(expected = GrammarException.class)
	public void test() throws GrammarException {
		Grammar grammar = TestGrammars.getGrammarFromLRkPamphlet();
		LR0ParserTable table = new LR0ParserTable(grammar);
		table.getAction(5, new Terminal("b", null, false));
		fail("An GrammarException was expected due to the fact that this grammar is not LR(0)!");
	}

	@Test(expected = GrammarException.class)
	public void test2() throws GrammarException {
		Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
		LR0ParserTable table = new LR0ParserTable(grammar);
		table.getAction(2, new Terminal("STAR", "*", false));
		fail("An GrammarException was expected due to the fact that this grammar is not LR(0)!");
	}

}
