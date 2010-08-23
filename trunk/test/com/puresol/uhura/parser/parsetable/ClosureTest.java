package com.puresol.uhura.parser.parsetable;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.parser.parsetable.Closure0;
import com.puresol.uhura.parser.parsetable.Item;
import com.puresol.uhura.parser.parsetable.ItemSet;

import junit.framework.TestCase;

public class ClosureTest extends TestCase {

	@Test
	public void test() {
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
		System.out.println("Productions:");
		grammar.printProductions();

		Closure0 closure = new Closure0(grammar.getProductions());
		System.out.println("Closure0:");
		Item primItem = new Item(grammar.getProductions().get("Z").get(0), 0);
		ItemSet itemSet = closure.closure(primItem);
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getPrimaryItems().size());
		assertEquals(primItem, itemSet.getPrimaryItems().iterator().next());

		Set<Item> addedItems = itemSet.getAddedItems();
		assertNotNull(addedItems);
		assertEquals(2, addedItems.size());
		Iterator<Item> iterator = addedItems.iterator();
		Item item1 = iterator.next();
		assertEquals(0, item1.getPosition());
		assertEquals(grammar.getProductions().getProductions().get(1),
				item1.getProduction());
		Item item2 = iterator.next();
		assertEquals(0, item2.getPosition());
		assertEquals(grammar.getProductions().getProductions().get(2),
				item2.getProduction());
	}
}
