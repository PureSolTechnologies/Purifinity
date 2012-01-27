package com.puresol.uhura.parser.functions;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.parser.items.LR0Item;
import com.puresol.uhura.parser.items.LR0ItemSet;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class Closure0Test {

	@Test
	public void testPersistence() {
		Grammar grammar = TestGrammars.getLR1TestGrammarFromDragonBook();
		Closure0 closure0 = new Closure0(grammar);
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				Closure0.class).toString()
				+ ".persist");
		PersistenceTester.test(closure0, file);
	}

	@Test
	public void testFromLR1Pamphlet() {
		Grammar grammar = TestGrammars.getGrammarFromLRkPamphlet();
		System.out.println("Productions:");
		System.out.println(grammar.toProductionsString());

		Closure0 closure = new Closure0(grammar);
		System.out.println("Closure0:");
		LR0Item primItem = new LR0Item(grammar.getProductions().get(0), 0);
		LR0ItemSet itemSet = closure.calc(primItem);
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getKernelItems().size());
		assertEquals(primItem, itemSet.getKernelItems().iterator().next());

		Set<LR0Item> addedItems = itemSet.getNonKernelItems();
		assertNotNull(addedItems);
		assertEquals(2, addedItems.size());
		Iterator<LR0Item> iterator = addedItems.iterator();
		LR0Item item1 = iterator.next();
		assertEquals(0, item1.getPosition());
		assertEquals(grammar.getProductions().getList().get(1),
				item1.getProduction());
		LR0Item item2 = iterator.next();
		assertEquals(0, item2.getPosition());
		assertEquals(grammar.getProductions().getList().get(2),
				item2.getProduction());
	}

	@Test
	public void testFromDragonBook() {
		Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
		System.out.println("Productions:");
		System.out.println(grammar.toProductionsString());

		Closure0 closure = new Closure0(grammar);
		System.out.println("Closure0:");
		LR0Item primItem = new LR0Item(grammar.getProductions().get(0), 0);
		LR0ItemSet itemSet = closure.calc(primItem);
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getKernelItems().size());
		assertEquals(primItem, itemSet.getKernelItems().iterator().next());

		Set<LR0Item> addedItems = itemSet.getNonKernelItems();
		assertNotNull(addedItems);
		assertEquals(6, addedItems.size());
		Iterator<LR0Item> iterator = addedItems.iterator();
		LR0Item item1 = iterator.next();
		assertEquals(0, item1.getPosition());
		assertEquals(grammar.getProductions().getList().get(1),
				item1.getProduction());
		LR0Item item2 = iterator.next();
		assertEquals(0, item2.getPosition());
		assertEquals(grammar.getProductions().getList().get(2),
				item2.getProduction());
	}
}