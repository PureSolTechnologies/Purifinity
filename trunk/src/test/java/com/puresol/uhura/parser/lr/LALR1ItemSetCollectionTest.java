package com.puresol.uhura.parser.lr;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.parser.functions.Closure0;
import com.puresol.uhura.parser.functions.Closure1;
import com.puresol.uhura.parser.functions.First;
import com.puresol.uhura.parser.functions.Goto0;
import com.puresol.uhura.parser.items.LR1Item;
import com.puresol.uhura.parser.items.LR1ItemSet;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

/**
 * This test suite checks the LALR1ItemSetCollection class for right results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LALR1ItemSetCollectionTest {

	@Test
	public void testPersistence() {
		try {
			Grammar grammar = TestGrammars.getLALR1TestGrammarFromDragonBook();
			First first = new First(grammar);
			Closure0 closure0 = new Closure0(grammar);
			Goto0 goto0 = new Goto0(closure0);
			Closure1 closure1 = new Closure1(grammar, first);
			LR0ItemSetCollection lr0ItemSetCollection = new LR0ItemSetCollection(
					grammar, closure0, goto0);
			LR0StateTransitions lr0Transitions = new LR0StateTransitions(
					lr0ItemSetCollection, goto0);
			LALR1ItemSetCollection collection = new LALR1ItemSetCollection(
					grammar, lr0ItemSetCollection, lr0Transitions, closure1);
			File file = new File("test", FileUtilities
					.classToRelativePackagePath(LALR1ItemSetCollection.class)
					.toString()
					+ ".persist");
			PersistenceTester.test(collection, file);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	/**
	 * This test checks the result found on page 329 in Dragon book.
	 */
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
			LALR1ItemSetCollection itemSetCollection = new LALR1ItemSetCollection(
					grammar, lr0ItemSetCollection, lr0Transitions, closure1);

			System.out.println(itemSetCollection);
			assertEquals(10, itemSetCollection.getStateNumber());

			LR1ItemSet itemSet0 = itemSetCollection.getItemSet(0);
			assertEquals(1, itemSet0.getKernelItems().size());
			assertTrue(itemSet0.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(0), 0,
							FinishTerminal.getInstance())));

			LR1ItemSet itemSet1 = itemSetCollection.getItemSet(1);
			assertEquals(1, itemSet1.getKernelItems().size());
			assertTrue(itemSet1.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(0), 1,
							FinishTerminal.getInstance())));

			LR1ItemSet itemSet2 = itemSetCollection.getItemSet(2);
			assertEquals(2, itemSet2.getKernelItems().size());
			assertTrue(itemSet2.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(1), 1,
							FinishTerminal.getInstance())));
			assertTrue(itemSet2.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(5), 1,
							FinishTerminal.getInstance())));

			LR1ItemSet itemSet3 = itemSetCollection.getItemSet(3);
			assertEquals(1, itemSet3.getKernelItems().size());
			assertTrue(itemSet3.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(2), 1,
							FinishTerminal.getInstance())));

			LR1ItemSet itemSet4 = itemSetCollection.getItemSet(4);
			assertEquals(2, itemSet4.getKernelItems().size());
			assertTrue(itemSet4.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(3), 1,
							FinishTerminal.getInstance())));
			assertTrue(itemSet4.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(3), 1,
							new Terminal("EQUALS", "="))));

			LR1ItemSet itemSet5 = itemSetCollection.getItemSet(5);
			assertEquals(2, itemSet5.getKernelItems().size());
			assertTrue(itemSet5.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(4), 1,
							FinishTerminal.getInstance())));
			assertTrue(itemSet5.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(4), 1,
							new Terminal("EQUALS", "="))));

			LR1ItemSet itemSet6 = itemSetCollection.getItemSet(6);
			assertEquals(1, itemSet6.getKernelItems().size());
			assertTrue(itemSet6.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(1), 2,
							FinishTerminal.getInstance())));

			LR1ItemSet itemSet7 = itemSetCollection.getItemSet(7);
			assertEquals(2, itemSet7.getKernelItems().size());
			assertTrue(itemSet7.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(3), 2,
							FinishTerminal.getInstance())));
			assertTrue(itemSet7.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(3), 2,
							new Terminal("EQUALS", "="))));

			LR1ItemSet itemSet8 = itemSetCollection.getItemSet(8);
			assertEquals(2, itemSet8.getKernelItems().size());
			assertTrue(itemSet8.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(5), 1,
							FinishTerminal.getInstance())));
			assertTrue(itemSet8.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(5), 1,
							new Terminal("EQUALS", "="))));

			LR1ItemSet itemSet9 = itemSetCollection.getItemSet(9);
			assertEquals(1, itemSet9.getKernelItems().size());
			assertTrue(itemSet9.getKernelItems().contains(
					new LR1Item(grammar.getProductions().get(1), 3,
							FinishTerminal.getInstance())));

		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
