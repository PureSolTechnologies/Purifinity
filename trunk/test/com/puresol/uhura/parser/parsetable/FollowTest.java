package com.puresol.uhura.parser.parsetable;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishConstruction;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.parser.parsetable.Follow;

import junit.framework.TestCase;

public class FollowTest extends TestCase {

	@Test
	public void testLRPamphletGrammar() {
		System.out.println("=====================");
		System.out.println("LR Pampghlet Grammar:");
		System.out.println("=====================");
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
		System.out.println("Productions:");
		grammar.printProductions();

		Follow follow = new Follow(grammar);
		System.out.println("Follow:");
		System.out.println(follow.toString());

		Construction productionZ = new ProductionConstruction("Z");
		Construction productionS = new ProductionConstruction("S");
		Construction productionA = new ProductionConstruction("A");

		assertEquals(1, follow.get(productionZ).size());

		assertTrue(follow.get(productionZ).contains(
				FinishConstruction.getInstance()));

		assertEquals(3, follow.get(productionS).size());
		assertTrue(follow.get(productionS).contains(new TokenConstruction("b")));
		assertTrue(follow.get(productionS).contains(new TokenConstruction("c")));
		assertTrue(follow.get(productionS).contains(
				FinishConstruction.getInstance()));

		assertEquals(1, follow.get(productionA).size());
		assertTrue(follow.get(productionA).contains(new TokenConstruction("a")));
	}

	@Test
	public void testDragonBookGrammar() {
		System.out.println("====================");
		System.out.println("Dragon Book Grammar:");
		System.out.println("====================");
		Grammar grammar = TestGrammars.getTestGrammarFromDragonBook();
		System.out.println("Productions:");
		grammar.printProductions();

		Follow follow = new Follow(grammar);
		System.out.println("Follow:");
		System.out.println(follow.toString());

		Construction productionZ = new ProductionConstruction("Z");
		Construction productionE = new ProductionConstruction("E");
		Construction productionT = new ProductionConstruction("T");
		Construction productionF = new ProductionConstruction("F");

		assertEquals(1, follow.get(productionZ).size());
		assertTrue(follow.get(productionZ).contains(
				FinishConstruction.getInstance()));

	}
}
