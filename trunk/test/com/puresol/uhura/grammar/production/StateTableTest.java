package com.puresol.uhura.grammar.production;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;

import junit.framework.TestCase;

public class StateTableTest extends TestCase {

	@Test
	public void testCalculateTable() {
		try {
			Grammar grammar = new Grammar();

			Production prodS = new Production("S");
			prodS.addElement(new ProductionConstruction("E"));

			Production prodE1 = new Production("E");
			prodE1.addElement(new ProductionConstruction("E"));
			prodE1.addElement(new TextConstruction("*"));
			prodE1.addElement(new ProductionConstruction("B"));

			Production prodE2 = new Production("E");
			prodE2.addElement(new ProductionConstruction("E"));
			prodE2.addElement(new TextConstruction("+"));
			prodE2.addElement(new ProductionConstruction("B"));

			Production prodE3 = new Production("E");
			prodE3.addElement(new ProductionConstruction("B"));

			Production prodB1 = new Production("B");
			prodB1.addElement(new TextConstruction("0"));

			Production prodB2 = new Production("B");
			prodB2.addElement(new TextConstruction("1"));

			ProductionSet productions = new ProductionSet();
			productions.addRule(prodS);
			productions.addRule(prodE1);
			productions.addRule(prodE2);
			productions.addRule(prodE3);
			productions.addRule(prodB1);
			productions.addRule(prodB2);
			grammar.setProductions(productions);
			grammar.printProductions();
			System.out.println();
			StateTable table = new StateTable(grammar);
			System.out.println(table.toString());
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!!!");
		}
	}

}
