package com.puresol.uhura.parser.functions;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.parser.functions.Closure0;
import com.puresol.uhura.parser.functions.Goto0;
import com.puresol.uhura.parser.items.LR0Item;
import com.puresol.uhura.parser.items.LR0ItemSet;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class Goto0Test {

	@Test
	public void testPersistence() {
		Grammar grammar = TestGrammars.getLR1TestGrammarFromDragonBook();
		Closure0 closure0 = new Closure0(grammar);
		Goto0 goto0 = new Goto0(closure0);
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				Goto0.class).toString()
				+ ".persist");
		PersistenceTester.test(goto0, file);
	}

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

		itemSet = gotoCalc.calc(itemSet, new Terminal("b", null, false));
		System.out.println(itemSet.toString());

		assertEquals(1, itemSet.getKernelItems().size());
		assertEquals(3, itemSet.getNonKernelItems().size());
	}
}
