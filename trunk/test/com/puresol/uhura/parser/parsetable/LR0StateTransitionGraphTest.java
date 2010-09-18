package com.puresol.uhura.parser.parsetable;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;

import junit.framework.TestCase;

public class LR0StateTransitionGraphTest extends TestCase {

	@Test
	public void testLRPamphletGrammar() {
		try {
			System.out.println("====================");
			System.out.println("LR Pamphlet Grammar:");
			System.out.println("====================");
			Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();

			LR0StateTransitionGraph transitionGraph;
			transitionGraph = new LR0StateTransitionGraph(grammar);
			System.out.println(transitionGraph);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testDragonBookGrammar() {
		try {
			System.out.println("====================");
			System.out.println("Dragon Book Grammar:");
			System.out.println("====================");
			Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();

			LR0StateTransitionGraph transitionGraph = new LR0StateTransitionGraph(
					grammar);
			System.out.println(transitionGraph);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
