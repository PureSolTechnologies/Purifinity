package com.puresol.uhura.grammar.production;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.puresol.uhura.grammar.GrammarException;

/**
 * This class contains all parser rules which are to be set for a parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProductionSet {

	private final ConcurrentMap<String, List<Production>> name2Production = new ConcurrentHashMap<String, List<Production>>();
	private final List<Production> productions = new CopyOnWriteArrayList<Production>();

	public void addRule(Production production) throws GrammarException {
		if (production == null) {
			return;
		}
		if (productions.contains(production)) {
			throw new GrammarException("Double defined production '"
					+ production + "'!");
		}
		productions.add(production);
		if (!name2Production.containsKey(production.getName())) {
			name2Production.put(production.getName(),
					new CopyOnWriteArrayList<Production>());
		}
		name2Production.get(production.getName()).add(production);
	}

	public List<Production> getProductions() {
		return productions;
	}

	public List<Production> get(String productionName) {
		return name2Production.get(productionName);
	}
}
