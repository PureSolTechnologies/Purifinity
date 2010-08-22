package com.puresol.uhura.parser.parsetable;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;

import junit.framework.TestCase;

public class StateTransitionGraphTest extends TestCase {

	@Test
	public void testLRPamphletGrammar() {
		System.out.println("====================");
		System.out.println("LR Pamphlet Grammar:");
		System.out.println("====================");
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();

		StateTransitionGraph transitionGraph = new StateTransitionGraph(grammar);
		System.out.println(transitionGraph);
	}

	@Test
	public void testDragonBookGrammar() {
		System.out.println("====================");
		System.out.println("Dragon Book Grammar:");
		System.out.println("====================");
		Grammar grammar = TestGrammars.getTestGrammarFromDragonBook();

		StateTransitionGraph transitionGraph = new StateTransitionGraph(grammar);
		System.out.println(transitionGraph);
	}

}
