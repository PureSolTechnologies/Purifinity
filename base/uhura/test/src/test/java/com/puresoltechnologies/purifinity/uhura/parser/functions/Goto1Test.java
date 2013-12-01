package com.puresoltechnologies.purifinity.uhura.parser.functions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.purifinity.uhura.grammar.Grammar;
import com.puresoltechnologies.purifinity.uhura.grammar.TestGrammars;
import com.puresoltechnologies.purifinity.uhura.grammar.production.FinishTerminal;
import com.puresoltechnologies.purifinity.uhura.grammar.production.Terminal;
import com.puresoltechnologies.purifinity.uhura.parser.functions.Closure1;
import com.puresoltechnologies.purifinity.uhura.parser.functions.First;
import com.puresoltechnologies.purifinity.uhura.parser.functions.Goto1;
import com.puresoltechnologies.purifinity.uhura.parser.items.LR1Item;
import com.puresoltechnologies.purifinity.uhura.parser.items.LR1ItemSet;

public class Goto1Test {

    @Test
    public void test() {
	Grammar grammar = TestGrammars.getGrammarFromLRkPamphlet();
	System.out.println("Productions:");
	System.out.println(grammar.toProductionsString());

	Closure1 closure = new Closure1(grammar, new First(grammar));
	System.out.println("Closure1:");
	LR1Item primItem = new LR1Item(grammar.getProductions().get(0), 0,
		FinishTerminal.getInstance());
	LR1ItemSet itemSet = closure.calc(primItem);
	System.out.println(itemSet.toString());

	Goto1 gotoCalc = new Goto1(new Closure1(grammar, new First(grammar)));
	System.out.println("Goto1:");

	itemSet = gotoCalc.calc(itemSet, new Terminal("b", null));
	System.out.println(itemSet.toString());

	assertEquals(2, itemSet.getKernelItems().size());
	assertEquals(3, itemSet.getNonKernelItems().size());
    }
}
