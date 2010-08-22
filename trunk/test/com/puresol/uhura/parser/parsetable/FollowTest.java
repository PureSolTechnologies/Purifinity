package com.puresol.uhura.parser.parsetable;

import java.util.Iterator;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishConstruction;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.TextConstruction;
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
		Construction finish = FinishConstruction.getInstance();
		Construction a = new TokenConstruction("a");
		Construction b = new TokenConstruction("b");
		Construction c = new TokenConstruction("c");

		assertEquals(1, follow.get(productionZ).size());
		Iterator<Construction> iterator = follow.get(productionZ).iterator();
		assertEquals(finish, iterator.next());

		assertEquals(1, follow.get(productionA).size());
		iterator = follow.get(productionA).iterator();
		assertEquals(a, iterator.next());

		assertEquals(3, follow.get(productionS).size());
		iterator = follow.get(productionS).iterator();
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(finish, iterator.next());
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
		Construction finish = FinishConstruction.getInstance();
		Construction plus = new TextConstruction("+");
		Construction star = new TextConstruction("*");
		Construction rParen = new TextConstruction(")");

		assertEquals(3, follow.get(productionE).size());
		Iterator<Construction> iterator = follow.get(productionE).iterator();
		assertEquals(plus, iterator.next());
		assertEquals(rParen, iterator.next());
		assertEquals(finish, iterator.next());

		assertEquals(4, follow.get(productionT).size());
		iterator = follow.get(productionT).iterator();
		assertEquals(star, iterator.next());
		assertEquals(plus, iterator.next());
		assertEquals(rParen, iterator.next());
		assertEquals(finish, iterator.next());

		assertEquals(4, follow.get(productionF).size());
		iterator = follow.get(productionF).iterator();
		assertEquals(star, iterator.next());
		assertEquals(plus, iterator.next());
		assertEquals(rParen, iterator.next());
		assertEquals(finish, iterator.next());

		assertEquals(1, follow.get(productionZ).size());
		iterator = follow.get(productionZ).iterator();
		assertEquals(finish, iterator.next());

	}

	@Test
	public void testLLDragonBookGrammar() {
		System.out.println("=======================");
		System.out.println("Dragon Book LL Grammar:");
		System.out.println("=======================");
		Grammar grammar = TestGrammars.getTestLLGrammarFromDragonBook();
		System.out.println("Productions:");
		grammar.printProductions();

		Follow follow = new Follow(grammar);
		System.out.println("Follow:");
		System.out.println(follow.toString());

		Construction productionE = new ProductionConstruction("E");
		Construction productionES = new ProductionConstruction("E'");
		Construction productionT = new ProductionConstruction("T");
		Construction productionTS = new ProductionConstruction("T'");
		Construction productionF = new ProductionConstruction("F");
		Construction finish = FinishConstruction.getInstance();
		Construction plus = new TextConstruction("+");
		Construction star = new TextConstruction("*");
		Construction rParen = new TextConstruction(")");

		assertEquals(2, follow.get(productionE).size());
		Iterator<Construction> iterator = follow.get(productionE).iterator();
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(2, follow.get(productionES).size());
		iterator = follow.get(productionES).iterator();
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(3, follow.get(productionT).size());
		iterator = follow.get(productionT).iterator();
		assertEquals(plus, iterator.next());
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(3, follow.get(productionTS).size());
		iterator = follow.get(productionTS).iterator();
		assertEquals(plus, iterator.next());
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(4, follow.get(productionF).size());
		iterator = follow.get(productionF).iterator();
		assertEquals(star, iterator.next());
		assertEquals(plus, iterator.next());
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());
	}
}
