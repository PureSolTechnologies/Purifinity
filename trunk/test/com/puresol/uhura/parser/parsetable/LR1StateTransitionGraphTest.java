package com.puresol.uhura.parser.parsetable;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;

public class LR1StateTransitionGraphTest {

	@Test
	public void testLRPamphletGrammar() {
		try {
			System.out.println("====================");
			System.out.println("LR Pamphlet Grammar:");
			System.out.println("====================");
			Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();

			LR1StateTransitionGraph transitionGraph = new LR1StateTransitionGraph(
					grammar);
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
			Grammar grammar = TestGrammars.getLR1TestGrammarFromDragonBook();

			LR1StateTransitionGraph transitionGraph = new LR1StateTransitionGraph(
					grammar);
			System.out.println(transitionGraph);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
