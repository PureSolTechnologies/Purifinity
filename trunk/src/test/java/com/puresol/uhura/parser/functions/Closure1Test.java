package com.puresol.uhura.parser.functions;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.DummyTerminal;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.parser.functions.Closure1;
import com.puresol.uhura.parser.functions.First;
import com.puresol.uhura.parser.items.LR1Item;
import com.puresol.uhura.parser.items.LR1ItemSet;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

/**
 * This test suite checks the Closure1 class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Closure1Test {

	@Test
	public void testPersistence() {
		Grammar grammar = TestGrammars.getLR1TestGrammarFromDragonBook();
		First first = new First(grammar);
		Closure1 closure1 = new Closure1(grammar, first);
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				Closure1.class).toString()
				+ ".persist");
		PersistenceTester.test(closure1, file);
	}

	@Test
	public void testForGrammarFromLR1Pamphlet() {
		Grammar grammar = TestGrammars.getGrammarFromLRkPamphlet();
		if (Logger.getRootLogger().isDebugEnabled()) {
			System.out.println("Productions:");
			System.out.println(grammar.toProductionsString());
		}

		Closure1 closure = new Closure1(grammar, new First(grammar));
		if (Logger.getRootLogger().isDebugEnabled()) {
			System.out.println("Closure1:");
		}
		LR1Item primItem = new LR1Item(grammar.getProductions().get(0), 0,
				FinishTerminal.getInstance());
		LR1ItemSet itemSet = closure.calc(primItem);
		if (Logger.getRootLogger().isDebugEnabled()) {
			System.out.println(itemSet.toString());
		}

		assertEquals(1, itemSet.getKernelItems().size());
		assertEquals(primItem, itemSet.getKernelItems().iterator().next());

		Set<LR1Item> addedItems = itemSet.getNonKernelItems();
		assertNotNull(addedItems);
		assertEquals(4, addedItems.size());
		Iterator<LR1Item> iterator = addedItems.iterator();

		LR1Item item1 = iterator.next();
		assertEquals(0, item1.getPosition());
		assertEquals(grammar.getProductions().getList().get(1),
				item1.getProduction());
		assertEquals(FinishTerminal.getInstance(), item1.getLookahead());

		LR1Item item2 = iterator.next();
		assertEquals(0, item2.getPosition());
		assertEquals(grammar.getProductions().getList().get(2),
				item2.getProduction());
		assertEquals(FinishTerminal.getInstance(), item2.getLookahead());

		LR1Item item3 = iterator.next();
		assertEquals(0, item3.getPosition());
		assertEquals(grammar.getProductions().getList().get(1),
				item3.getProduction());
		assertEquals("b", item3.getLookahead().getName());

		LR1Item item4 = iterator.next();
		assertEquals(0, item4.getPosition());
		assertEquals(grammar.getProductions().getList().get(2),
				item4.getProduction());
		assertEquals("b", item4.getLookahead().getName());
	}

	/**
	 * This test is for results on page 327-328 in Dragon book.
	 */
	@Test
	public void testClosureForLALR1StartItem() {
		Grammar grammar = TestGrammars.getLALR1TestGrammarFromDragonBook();

		LR1Item lr1Item = new LR1Item(grammar.getProductions().get(0), 0,
				DummyTerminal.getInstance());
		First first = new First(grammar);
		Closure1 closure = new Closure1(grammar, first);
		LR1ItemSet lr1ItemSet = closure.calc(lr1Item);

		assertEquals(8, lr1ItemSet.getAllItems().size());

		assertEquals(1, lr1ItemSet.getKernelItems().size());
		assertTrue(lr1ItemSet.getKernelItems().contains(lr1Item));

		assertEquals(7, lr1ItemSet.getNonKernelItems().size());
		assertTrue(lr1ItemSet.getNonKernelItems().contains(
				new LR1Item(grammar.getProductions().get(1), 0, DummyTerminal
						.getInstance())));

		assertTrue(lr1ItemSet.getNonKernelItems().contains(
				new LR1Item(grammar.getProductions().get(2), 0, DummyTerminal
						.getInstance())));
		assertTrue(lr1ItemSet.getNonKernelItems().contains(
				new LR1Item(grammar.getProductions().get(3), 0, new Terminal(
						"EQUALS", "=", false))));
		assertTrue(lr1ItemSet.getNonKernelItems().contains(
				new LR1Item(grammar.getProductions().get(4), 0, new Terminal(
						"EQUALS", "=", false))));
		assertTrue(lr1ItemSet.getNonKernelItems().contains(
				new LR1Item(grammar.getProductions().get(5), 0, DummyTerminal
						.getInstance())));
		assertTrue(lr1ItemSet.getNonKernelItems().contains(
				new LR1Item(grammar.getProductions().get(3), 0, DummyTerminal
						.getInstance())));
		assertTrue(lr1ItemSet.getNonKernelItems().contains(
				new LR1Item(grammar.getProductions().get(4), 0, DummyTerminal
						.getInstance())));
	}
}
