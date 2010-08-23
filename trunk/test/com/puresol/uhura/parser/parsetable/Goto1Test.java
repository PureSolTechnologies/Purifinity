package com.puresol.uhura.parser.parsetable;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.FinishConstruction;
import com.puresol.uhura.grammar.production.TokenConstruction;

import junit.framework.TestCase;

public class Goto1Test extends TestCase {

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

		Goto1 gotoCalc = new Goto1(grammar);
		System.out.println("Goto1:");

		itemSet = gotoCalc.calc(itemSet, new TokenConstruction("b"));
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getPrimaryItems().size());
		assertEquals(3, itemSet.getAddedItems().size());
	}
}
