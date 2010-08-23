package com.puresol.uhura.parser.parsetable;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.parser.parsetable.Closure0;
import com.puresol.uhura.parser.parsetable.Goto0;
import com.puresol.uhura.parser.parsetable.LR0Item;
import com.puresol.uhura.parser.parsetable.LR0ItemSet;

import junit.framework.TestCase;

public class Goto0Test extends TestCase {

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

		Goto0 gotoCalc = new Goto0(grammar);
		System.out.println("Goto0:");

		itemSet = gotoCalc.calc(itemSet, new TokenConstruction("b"));
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getPrimaryItems().size());
		assertEquals(3, itemSet.getAddedItems().size());
	}
}
