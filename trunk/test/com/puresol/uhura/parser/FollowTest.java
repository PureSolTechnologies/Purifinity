package com.puresol.uhura.parser;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishConstruction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TokenConstruction;

import junit.framework.TestCase;

public class FollowTest extends TestCase {

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

			Follow follow = new Follow(grammar, new First(grammar));
			System.out.println("Follow1:");
			System.out.println(follow.toString());

			Construction productionZ = new ProductionConstruction("Z");
			Construction productionS = new ProductionConstruction("S");
			Construction productionA = new ProductionConstruction("A");

			assertEquals(1, follow.get(productionZ).size());

			assertTrue(follow.get(productionZ).contains(
					FinishConstruction.getInstance()));

			assertEquals(3, follow.get(productionS).size());
			assertTrue(follow.get(productionS).contains(
					new TokenConstruction("b")));
			assertTrue(follow.get(productionS).contains(
					new TokenConstruction("c")));
			assertTrue(follow.get(productionS).contains(
					FinishConstruction.getInstance()));

			assertEquals(1, follow.get(productionA).size());
			assertTrue(follow.get(productionA).contains(
					new TokenConstruction("a")));
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
