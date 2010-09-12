package com.puresol.uhura.parser.parsetable;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.parser.parsetable.LR1Item;
import com.puresol.uhura.parser.parsetable.LR1ItemSet;

import junit.framework.TestCase;

public class Closure1Test extends TestCase {

	@Test
	public void test() {
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
		System.out.println("Productions:");
		System.out.println(grammar.toProductionsString());

		Closure1 closure = new Closure1(grammar);
		System.out.println("Closure1:");
		LR1Item primItem = new LR1Item(grammar.getProductions().get(0), 0);
		primItem.addLookahead(FinishTerminal.getInstance());
		LR1ItemSet itemSet = closure.calc(primItem);
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getPrimaryItems().size());
		assertEquals(primItem, itemSet.getPrimaryItems().iterator().next());

		Set<LR1Item> addedItems = itemSet.getAddedItems();
		assertNotNull(addedItems);
		assertEquals(4, addedItems.size());
		Iterator<LR1Item> iterator = addedItems.iterator();

		LR1Item item1 = iterator.next();
		assertEquals(0, item1.getPosition());
		assertEquals(grammar.getProductions().getList().get(1),
				item1.getProduction());
		assertEquals(1, item1.getLookahead().size());
		assertEquals(FinishTerminal.getInstance(), item1.getLookahead()
				.iterator().next());

		LR1Item item2 = iterator.next();
		assertEquals(0, item2.getPosition());
		assertEquals(grammar.getProductions().getList().get(1),
				item2.getProduction());
		assertEquals(1, item2.getLookahead().size());
		assertEquals("b", item2.getLookahead().iterator().next().getName());

		LR1Item item3 = iterator.next();
		assertEquals(0, item3.getPosition());
		assertEquals(grammar.getProductions().getList().get(2),
				item3.getProduction());
		assertEquals(1, item3.getLookahead().size());
		assertEquals("b", item3.getLookahead().iterator().next().getName());

		LR1Item item4 = iterator.next();
		assertEquals(0, item4.getPosition());
		assertEquals(grammar.getProductions().getList().get(2),
				item4.getProduction());
		assertEquals(1, item4.getLookahead().size());
		assertEquals(FinishTerminal.getInstance(), item4.getLookahead()
				.iterator().next());
	}
}
