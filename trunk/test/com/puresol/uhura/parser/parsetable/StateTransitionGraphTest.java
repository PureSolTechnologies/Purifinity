package com.puresol.uhura.parser.parsetable;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;

import junit.framework.TestCase;

public class StateTransitionGraphTest extends TestCase {

	@Test
	public void test() {
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
		System.out.println("Productions:");
		grammar.printProductions();

		StateTransitionGraph transitionGraph = new StateTransitionGraph(grammar);
		transitionGraph.println();
	}

}
