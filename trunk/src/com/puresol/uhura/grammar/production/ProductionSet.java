package com.puresol.uhura.grammar.production;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This class contains all parser rules which are to be set for a parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProductionSet {

	private final ConcurrentMap<String, Set<Production>> name2Production = new ConcurrentHashMap<String, Set<Production>>();
	private final Set<Production> productions = new CopyOnWriteArraySet<Production>();

	public void addRule(Production production) {
		productions.add(production);
		if (!name2Production.containsKey(production.getName())) {
			name2Production.put(production.getName(),
					new CopyOnWriteArraySet<Production>());
		}
		name2Production.get(production.getName()).add(production);
	}

	public Set<Production> getProductions() {
		return productions;
	}

	public Set<Production> get(String productionName) {
		return name2Production.get(productionName);
	}
}
