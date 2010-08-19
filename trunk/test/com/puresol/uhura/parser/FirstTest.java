package com.puresol.uhura.parser;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TokenConstruction;

import junit.framework.TestCase;

public class FirstTest extends TestCase {

	@Test
	public void test() {
		try {
			ProductionSet productions = new ProductionSet();

			Production production = new Production("Z");
			production.addElement(new ProductionConstruction("S"));
			productions.addRule(production);

			production = new Production("S");
			production.addElement(new ProductionConstruction("S"));
			production.addElement(new TokenConstruction("b"));
			productions.addRule(production);

			production = new Production("S");
			production.addElement(new TokenConstruction("b"));
			production.addElement(new ProductionConstruction("A"));
			production.addElement(new TokenConstruction("a"));
			productions.addRule(production);

			production = new Production("A");
			production.addElement(new TokenConstruction("a"));
			production.addElement(new ProductionConstruction("S"));
			production.addElement(new TokenConstruction("c"));
			productions.addRule(production);

			production = new Production("A");
			production.addElement(new TokenConstruction("a"));
			productions.addRule(production);

			production = new Production("A");
			production.addElement(new TokenConstruction("a"));
			production.addElement(new ProductionConstruction("S"));
			production.addElement(new TokenConstruction("b"));
			productions.addRule(production);

			Grammar grammar = new Grammar();
			grammar.setProductions(productions);
			System.out.println("Productions:");
			grammar.printProductions();

			First first = new First(grammar);
			System.out.println("First1:");
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
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
