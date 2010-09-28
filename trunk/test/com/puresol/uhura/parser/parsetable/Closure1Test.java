package com.puresol.uhura.parser.parsetable;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.parser.parsetable.LR1Item;
import com.puresol.uhura.parser.parsetable.LR1ItemSet;

public class Closure1Test {

	@Test
	public void test() {
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
		System.out.println("Productions:");
		System.out.println(grammar.toProductionsString());

		Closure1 closure = new Closure1(grammar, new First(grammar));
		System.out.println("Closure1:");
		LR1Item primItem = new LR1Item(grammar.getProductions().get(0), 0,
				FinishTerminal.getInstance());
		LR1ItemSet itemSet = closure.calc(primItem);
		System.out.println(itemSet.toString());

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
}
