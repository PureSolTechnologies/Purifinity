package com.puresol.uhura.parser.parsetable;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Terminal;

public class LR1StateTransitionsTest {

	/**
	 * This test is from picture 4.41 from German edition of the Dragon Book at
	 * page 317.
	 */
	@Test
	public void test() {
		try {
			Grammar grammar = TestGrammars.getLR1TestGrammarFromDragonBook();
			First first = new First(grammar);
			Closure1 closure1 = new Closure1(grammar, first);
			Goto1 goto1 = new Goto1(closure1);
			LR1ItemSetCollection itemSetCollection = new LR1ItemSetCollection(
					grammar, closure1, goto1);
			LR1StateTransitions transitions = new LR1StateTransitions(
					itemSetCollection, goto1);

			assertEquals(4, transitions.getTransitions(0).size());
			assertTrue(transitions.getTransition(0, new NonTerminal("S")) == 1);
			assertTrue(transitions.getTransition(0, new NonTerminal("C")) == 2);
			assertTrue(transitions.getTransition(0, new Terminal("c")) == 3);
			assertTrue(transitions.getTransition(0, new Terminal("d")) == 4);

			assertEquals(0, transitions.getTransitions(1).size());

			assertEquals(3, transitions.getTransitions(2).size());
			assertTrue(transitions.getTransition(2, new NonTerminal("C")) == 5);
			assertTrue(transitions.getTransition(2, new Terminal("c")) == 6);
			assertTrue(transitions.getTransition(2, new Terminal("d")) == 7);

			assertEquals(3, transitions.getTransitions(3).size());
			assertTrue(transitions.getTransition(3, new NonTerminal("C")) == 8);
			assertTrue(transitions.getTransition(3, new Terminal("c")) == 3);
			assertTrue(transitions.getTransition(3, new Terminal("d")) == 4);

			assertEquals(0, transitions.getTransitions(4).size());

			assertEquals(0, transitions.getTransitions(5).size());

			assertEquals(3, transitions.getTransitions(6).size());
			assertTrue(transitions.getTransition(6, new NonTerminal("C")) == 9);
			assertTrue(transitions.getTransition(6, new Terminal("c")) == 6);
			assertTrue(transitions.getTransition(6, new Terminal("d")) == 7);

			assertEquals(0, transitions.getTransitions(7).size());

			assertEquals(0, transitions.getTransitions(8).size());

			assertEquals(0, transitions.getTransitions(9).size());

		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
