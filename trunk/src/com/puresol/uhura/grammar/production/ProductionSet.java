package com.puresol.uhura.grammar.production;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This class contains all parser rules which are to be set for a parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProductionSet {

	private final Set<Production> rules = new CopyOnWriteArraySet<Production>();

	public void addRule(Production rule) {
		rules.add(rule);
	}

	public Set<Production> getRuleSet() {
		return rules;
	}
}
