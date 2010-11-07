package com.puresol.uhura.parser.lr;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.parser.functions.Closure0;
import com.puresol.uhura.parser.functions.Goto0;
import com.puresol.uhura.parser.items.LR0ItemSetCollection;

public class LR0ItemSetCollectionTest {

	@Test
	public void testLRPamphletGrammar() {
		try {
			System.out.println("====================");
			System.out.println("LR Pamphlet Grammar:");
			System.out.println("====================");
			Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();

			Closure0 closure0 = new Closure0(grammar);
			Goto0 goto0 = new Goto0(closure0);
			LR0ItemSetCollection transitionGraph = new LR0ItemSetCollection(
					grammar, closure0, goto0);
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

			Closure0 closure0 = new Closure0(grammar);
			Goto0 goto0 = new Goto0(closure0);
			LR0ItemSetCollection transitionGraph = new LR0ItemSetCollection(
					grammar, closure0, goto0);
			System.out.println(transitionGraph);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testDragonBookGrammar2() {
		try {
			System.out.println("======================");
			System.out.println("Dragon Book Grammar 2:");
			System.out.println("======================");
			Grammar grammar = TestGrammars.getLALR1TestGrammarFromDragonBook();

			Closure0 closure0 = new Closure0(grammar);
			Goto0 goto0 = new Goto0(closure0);
			LR0ItemSetCollection transitionGraph = new LR0ItemSetCollection(
					grammar, closure0, goto0);
			System.out.println(transitionGraph);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
