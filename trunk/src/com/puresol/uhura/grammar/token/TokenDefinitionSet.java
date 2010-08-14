package com.puresol.uhura.grammar.token;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class TokenDefinitionSet {

	private static final long serialVersionUID = 4992743487086731635L;

	private final Set<TokenDefinition> rules = new CopyOnWriteArraySet<TokenDefinition>();

	public void addRule(TokenDefinition rule) {
		rules.add(rule);
	}

	public Set<TokenDefinition> getRules() {
		return rules;
	}
}
