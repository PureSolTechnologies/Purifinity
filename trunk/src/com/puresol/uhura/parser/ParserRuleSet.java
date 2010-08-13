package com.puresol.uhura.parser;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This class contains all parser rules which are to be set for a parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParserRuleSet {

	private final Set<ParserRule> rules = new CopyOnWriteArraySet<ParserRule>();

	public void addRule(ParserRule rule) {
		rules.add(rule);
	}

	public Set<ParserRule> getRuleSet() {
		return rules;
	}
}
