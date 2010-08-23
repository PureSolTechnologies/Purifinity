package com.puresol.uhura.parser.parsetable;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.FinishConstruction;
import com.puresol.uhura.parser.parsetable.LR1Item;
import com.puresol.uhura.parser.parsetable.LR1ItemSet;

import junit.framework.TestCase;

public class Closure1Test extends TestCase {

	@Test
	public void test() {
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
		System.out.println("Productions:");
		grammar.printProductions();

		Closure1 closure = new Closure1(grammar);
		System.out.println("Closure1:");
		LR1Item primItem = new LR1Item(
				grammar.getProductions().get("Z").get(0), 0);
		primItem.addLookahead(FinishConstruction.getInstance());
		LR1ItemSet itemSet = closure.calc(primItem);
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getPrimaryItems().size());
		assertEquals(primItem, itemSet.getPrimaryItems().iterator().next());

		Set<LR1Item> addedItems = itemSet.getAddedItems();
		assertNotNull(addedItems);
		assertEquals(2, addedItems.size());
		Iterator<LR1Item> iterator = addedItems.iterator();
		LR1Item item1 = iterator.next();
		assertEquals(0, item1.getPosition());
		assertEquals(grammar.getProductions().getProductions().get(1),
				item1.getProduction());
		LR1Item item2 = iterator.next();
		assertEquals(0, item2.getPosition());
		assertEquals(grammar.getProductions().getProductions().get(2),
				item2.getProduction());
	}
}
