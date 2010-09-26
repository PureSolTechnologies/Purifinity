package com.puresol.uhura.parser.parsetable;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;

public class LALR1StateTransitionGraphTest {

	@Test
	public void testDragonBookGrammar() {
		try {
			System.out.println("============================");
			System.out.println("Dragon Book LALR(1) Grammar:");
			System.out.println("============================");
			Logger.getRootLogger().setLevel(Level.TRACE);
			Grammar grammar = TestGrammars.getLALR1TestGrammarFromDragonBook();
			System.out.println(grammar);

			/* LALR1StateTransitionGraph transitionGraph = */
			new LALR1StateTransitionGraph(grammar);
			// System.out.println(transitionGraph);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
