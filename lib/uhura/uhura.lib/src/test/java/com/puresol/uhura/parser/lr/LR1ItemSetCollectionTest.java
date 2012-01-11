package com.puresol.uhura.parser.lr;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.parser.functions.Closure1;
import com.puresol.uhura.parser.functions.First;
import com.puresol.uhura.parser.functions.Goto1;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class LR1ItemSetCollectionTest {

	@Test
	public void testPersistence() {
		try {
			Grammar grammar = TestGrammars.getLR1TestGrammarFromDragonBook();
			First first = new First(grammar);
			Closure1 closure1 = new Closure1(grammar, first);
			Goto1 goto1 = new Goto1(closure1);
			LR1ItemSetCollection collection = new LR1ItemSetCollection(grammar,
					closure1, goto1);
			File file = new File("test", FileUtilities
					.classToRelativePackagePath(LR1ItemSetCollection.class)
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

			First first = new First(grammar);
			Closure1 closure1 = new Closure1(grammar, first);
			Goto1 goto1 = new Goto1(closure1);
			LR1ItemSetCollection transitionGraph = new LR1ItemSetCollection(
					grammar, closure1, goto1);
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

			First first = new First(grammar);
			Closure1 closure1 = new Closure1(grammar, first);
			Goto1 goto1 = new Goto1(closure1);
			LR1ItemSetCollection transitionGraph = new LR1ItemSetCollection(
					grammar, closure1, goto1);
			System.out.println(transitionGraph);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}