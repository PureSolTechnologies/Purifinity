package com.puresol.uhura.grammar.production;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This class contains all parser rules which are to be set for a parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProductionSet {

	private final Set<Production> productions = new CopyOnWriteArraySet<Production>();

	public void addRule(Production production) {
		productions.add(production);
	}

	public Set<Production> getProductions() {
		return productions;
	}

	public List<Production> get(String productionName) {
		List<Production> namedProductions = new ArrayList<Production>();
		for (Production production : productions) {
			if (production.getName().equals(productionName)) {
				namedProductions.add(production);
			}
		}
		return namedProductions;
	}
}
