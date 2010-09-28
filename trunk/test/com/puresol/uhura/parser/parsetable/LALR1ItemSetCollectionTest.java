package com.puresol.uhura.parser.parsetable;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;

public class LALR1ItemSetCollectionTest {

	@Test
	public void testDragonBookGrammar() {
		try {
			System.out.println("============================");
			System.out.println("Dragon Book LALR(1) Grammar:");
			System.out.println("============================");
			Logger.getRootLogger().setLevel(Level.TRACE);
			Grammar grammar = TestGrammars.getLALR1TestGrammarFromDragonBook();
			System.out.println(grammar);

			First first = new First(grammar);
			Closure0 closure0 = new Closure0(grammar);
			Goto0 goto0 = new Goto0(closure0);
			Closure1 closure1 = new Closure1(grammar, first);
			LR0ItemSetCollection lr0ItemSetCollection = new LR0ItemSetCollection(
					grammar, closure0, goto0);
			LR0StateTransitions lr0Transitions = new LR0StateTransitions(
					lr0ItemSetCollection, goto0);
			/* LALR1StateTransitionGraph transitionGraph = */
			new LALR1ItemSetCollection(grammar, lr0ItemSetCollection,
					lr0Transitions, closure1);
			// System.out.println(transitionGraph);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
