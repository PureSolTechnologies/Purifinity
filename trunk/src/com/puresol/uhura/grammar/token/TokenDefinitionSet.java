package com.puresol.uhura.grammar.token;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.puresol.uhura.grammar.GrammarException;

public class TokenDefinitionSet {

	private static final long serialVersionUID = 4992743487086731635L;

	private final List<TokenDefinition> tokenDefinitions = new CopyOnWriteArrayList<TokenDefinition>();

	public void addDefinition(TokenDefinition rule) throws GrammarException {
		if (rule == null) {
			return;
		}
		if (tokenDefinitions.contains(rule)) {
			throw new GrammarException("Double defined token definition '"
					+ rule + "'!");
		}
		tokenDefinitions.add(rule);
	}

	public List<TokenDefinition> getRules() {
		return tokenDefinitions;
	}
}
