package com.puresoltechnologies.purifinity.uhura.parser.functions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.purifinity.uhura.grammar.Grammar;
import com.puresoltechnologies.purifinity.uhura.grammar.TestGrammars;
import com.puresoltechnologies.purifinity.uhura.grammar.production.Terminal;
import com.puresoltechnologies.purifinity.uhura.parser.functions.Closure0;
import com.puresoltechnologies.purifinity.uhura.parser.functions.Goto0;
import com.puresoltechnologies.purifinity.uhura.parser.items.LR0Item;
import com.puresoltechnologies.purifinity.uhura.parser.items.LR0ItemSet;

public class Goto0Test {

    @Test
    public void test() {
	Grammar grammar = TestGrammars.getGrammarFromLRkPamphlet();
	System.out.println("Productions:");
	System.out.println(grammar.toProductionsString());

	Closure0 closure = new Closure0(grammar);
	System.out.println("Closure0:");
	LR0Item primItem = new LR0Item(grammar.getProductions().get(0), 0);
	LR0ItemSet itemSet = closure.calc(primItem);
	System.out.println(itemSet.toString());

	Goto0 gotoCalc = new Goto0(new Closure0(grammar));
	System.out.println("Goto0:");

	itemSet = gotoCalc.calc(itemSet, new Terminal("b", null));
	System.out.println(itemSet.toString());

	assertEquals(1, itemSet.getKernelItems().size());
	assertEquals(3, itemSet.getNonKernelItems().size());
    }
}
