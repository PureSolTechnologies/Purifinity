package com.puresol.uhura.parser;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.parser.parsetable.Item;
import com.puresol.uhura.parser.parsetable.ItemSet;

import junit.framework.TestCase;

public class GotoTest extends TestCase {

	@Test
	public void test() {
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
		System.out.println("Productions:");
		grammar.printProductions();

		Closure closure = new Closure(grammar.getProductions());
		System.out.println("Closure0:");
		Item primItem = new Item(grammar.getProductions().get("Z").get(0), 0);
		ItemSet itemSet = closure.closure(primItem);
		System.out.println(itemSet.toString());

		Goto gotoCalc = new Goto(grammar.getProductions());
		System.out.println("Goto0:");

		itemSet = gotoCalc.goto0(itemSet, new TokenConstruction("b"));
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getPrimaryItems().size());
		assertEquals(3, itemSet.getAddedItems().size());
	}
}
