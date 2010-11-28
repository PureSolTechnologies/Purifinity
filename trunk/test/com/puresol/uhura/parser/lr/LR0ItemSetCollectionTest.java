package com.puresol.uhura.parser.lr;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.parser.functions.Closure0;
import com.puresol.uhura.parser.functions.Goto0;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class LR0ItemSetCollectionTest {

	@Test
	public void testPersistence() {
		try {
			Grammar grammar = TestGrammars.getGrammarFromLRkPamphlet();

			Closure0 closure0 = new Closure0(grammar);
			Goto0 goto0 = new Goto0(closure0);
			LR0ItemSetCollection collection = new LR0ItemSetCollection(grammar,
					closure0, goto0);
			File file = new File("test", FileUtilities
					.classToRelativePackagePath(LR0ItemSetCollection.class)
					.toString()
					+ ".persist");
			PersistenceTester.test(collection, file);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testLRPamphletGrammar() {
		try {
			System.out.println("====================");
			System.out.println("LR Pamphlet Grammar:");
			System.out.println("====================");
			Grammar grammar = TestGrammars.getGrammarFromLRkPamphlet();

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
