package com.puresol.uhura.parser.parsetable;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.parser.parsetable.Closure0;
import com.puresol.uhura.parser.parsetable.LR0Item;
import com.puresol.uhura.parser.parsetable.LR0ItemSet;

import junit.framework.TestCase;

public class Closure0Test extends TestCase {

	@Test
	public void test() {
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
		System.out.println("Productions:");
		grammar.printProductions();

		Closure0 closure = new Closure0(grammar);
		System.out.println("Closure0:");
		LR0Item primItem = new LR0Item(
				grammar.getProductions().get("Z").get(0), 0);
		LR0ItemSet itemSet = closure.calc(primItem);
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getPrimaryItems().size());
		assertEquals(primItem, itemSet.getPrimaryItems().iterator().next());

		Set<LR0Item> addedItems = itemSet.getAddedItems();
		assertNotNull(addedItems);
		assertEquals(2, addedItems.size());
		Iterator<LR0Item> iterator = addedItems.iterator();
		LR0Item item1 = iterator.next();
		assertEquals(0, item1.getPosition());
		assertEquals(grammar.getProductions().getProductions().get(1),
				item1.getProduction());
		LR0Item item2 = iterator.next();
		assertEquals(0, item2.getPosition());
		assertEquals(grammar.getProductions().getProductions().get(2),
				item2.getProduction());
	}
}
