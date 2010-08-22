package com.puresol.uhura.parser.parsetable;

import java.util.Iterator;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.EmptyConstruction;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.TextConstruction;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.parser.parsetable.First;

import junit.framework.TestCase;

public class FirstTest extends TestCase {

	@Test
	public void testLRPamphletGrammar() {
		System.out.println("=====================");
		System.out.println("LR Pampghlet Grammar:");
		System.out.println("=====================");
		Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
		System.out.println("Productions:");
		grammar.printProductions();

		First first = new First(grammar);
		System.out.println("First:");
		System.out.println(first.toString());

		Construction productionZ = new ProductionConstruction("Z");
		Construction productionS = new ProductionConstruction("S");
		Construction productionA = new ProductionConstruction("A");
		Construction terminalA = new TokenConstruction("a");
		Construction terminalB = new TokenConstruction("b");
		Construction terminalC = new TokenConstruction("c");

		assertEquals(1, first.get(productionZ).size());
		assertEquals(terminalB, first.get(productionZ).iterator().next());

		assertEquals(1, first.get(productionS).size());
		assertEquals(terminalB, first.get(productionS).iterator().next());

		assertEquals(1, first.get(productionA).size());
		assertEquals(terminalA, first.get(productionA).iterator().next());

		assertEquals(1, first.get(terminalA).size());
		assertEquals(terminalA, first.get(terminalA).iterator().next());

		assertEquals(1, first.get(terminalB).size());
		assertEquals(terminalB, first.get(terminalB).iterator().next());

		assertEquals(1, first.get(terminalC).size());
		assertEquals(terminalC, first.get(terminalC).iterator().next());
	}

	@Test
	public void testDragonBookGrammar() {
		System.out.println("====================");
		System.out.println("Dragon Book Grammar:");
		System.out.println("====================");
		Grammar grammar = TestGrammars.getTestGrammarFromDragonBook();
		System.out.println("Productions:");
		grammar.printProductions();

		First first = new First(grammar);
		System.out.println("First:");
		System.out.println(first.toString());

		Construction productionZ = new ProductionConstruction("Z");
		Construction productionE = new ProductionConstruction("E");
		Construction productionT = new ProductionConstruction("T");
		Construction productionF = new ProductionConstruction("F");
		Construction terminalId = new TokenConstruction("id");
		Construction terminalLParen = new TextConstruction("(");

		assertEquals(2, first.get(productionZ).size());
		Iterator<Construction> iterator = first.get(productionZ).iterator();
		assertEquals(terminalLParen, iterator.next());
		assertEquals(terminalId, iterator.next());

		assertEquals(2, first.get(productionE).size());
		iterator = first.get(productionE).iterator();
		assertEquals(terminalLParen, iterator.next());
		assertEquals(terminalId, iterator.next());

		assertEquals(2, first.get(productionT).size());
		iterator = first.get(productionT).iterator();
		assertEquals(terminalLParen, iterator.next());
		assertEquals(terminalId, iterator.next());

		assertEquals(2, first.get(productionF).size());
		iterator = first.get(productionF).iterator();
		assertEquals(terminalLParen, iterator.next());
		assertEquals(terminalId, iterator.next());

	}

	@Test
	public void testDragonBookLLGrammar() {
		System.out.println("=======================");
		System.out.println("Dragon Book LL Grammar:");
		System.out.println("=======================");
		Grammar grammar = TestGrammars.getTestLLGrammarFromDragonBook();
		System.out.println("Productions:");
		grammar.printProductions();

		First first = new First(grammar);
		System.out.println("First:");
		System.out.println(first.toString());

		Construction productionE = new ProductionConstruction("E");
		Construction productionES = new ProductionConstruction("E'");
		Construction productionT = new ProductionConstruction("T");
		Construction productionTS = new ProductionConstruction("T'");
		Construction productionF = new ProductionConstruction("F");
		Construction terminalId = new TokenConstruction("id");
		Construction terminalLParen = new TextConstruction("(");
		Construction terminalStar = new TextConstruction("*");
		Construction terminalPlus = new TextConstruction("+");
		Construction empty = EmptyConstruction.getInstance();

		assertEquals(2, first.get(productionE).size());
		Iterator<Construction> iterator = first.get(productionF).iterator();
		assertEquals(terminalLParen, iterator.next());
		assertEquals(terminalId, iterator.next());

		assertEquals(2, first.get(productionT).size());
		iterator = first.get(productionT).iterator();
		assertEquals(terminalLParen, iterator.next());
		assertEquals(terminalId, iterator.next());

		assertEquals(2, first.get(productionF).size());
		iterator = first.get(productionF).iterator();
		assertEquals(terminalLParen, iterator.next());
		assertEquals(terminalId, iterator.next());

		assertEquals(2, first.get(productionES).size());
		iterator = first.get(productionES).iterator();
		assertEquals(empty, iterator.next());
		assertEquals(terminalPlus, iterator.next());

		assertEquals(2, first.get(productionTS).size());
		iterator = first.get(productionTS).iterator();
		assertEquals(empty, iterator.next());
		assertEquals(terminalStar, iterator.next());

	}
}
