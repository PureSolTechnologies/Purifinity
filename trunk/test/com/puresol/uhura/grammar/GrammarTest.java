package com.puresol.uhura.grammar;

import org.junit.Test;

import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionProductionElement;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TextProductionElement;

import junit.framework.TestCase;

public class GrammarTest extends TestCase {

	@Test
	public void testCalculateTable() {
		try {
			Grammar grammar = new Grammar();

			Production prodS = new Production("S");
			prodS.addElement(new ProductionProductionElement("E"));

			Production prodE1 = new Production("E");
			prodE1.addElement(new ProductionProductionElement("E"));
			prodE1.addElement(new TextProductionElement("*"));
			prodE1.addElement(new ProductionProductionElement("B"));

			Production prodE2 = new Production("E");
			prodE2.addElement(new ProductionProductionElement("E"));
			prodE2.addElement(new TextProductionElement("+"));
			prodE2.addElement(new ProductionProductionElement("B"));

			Production prodE3 = new Production("E");
			prodE3.addElement(new ProductionProductionElement("B"));

			Production prodB1 = new Production("B");
			prodB1.addElement(new TextProductionElement("0"));

			Production prodB2 = new Production("B");
			prodB2.addElement(new TextProductionElement("1"));

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
			grammar.caluclateTable();

		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!!!");
		}
	}

}
