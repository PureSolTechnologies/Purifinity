package com.puresol.uhura.grammar.token;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.puresol.uhura.grammar.GrammarException;

public class TokenDefinitionSet {

	private final ConcurrentMap<String, TokenDefinition> name2Definition = new ConcurrentHashMap<String, TokenDefinition>();
	private final List<TokenDefinition> tokenDefinitions = new CopyOnWriteArrayList<TokenDefinition>();

	public void addDefinition(TokenDefinition definition)
			throws GrammarException {
		if (definition == null) {
			return;
		}
		if (tokenDefinitions.contains(definition)) {
			throw new GrammarException("Double defined token definition '"
					+ definition + "'!");
		}
		tokenDefinitions.add(definition);
		name2Definition.put(definition.getName(), definition);
	}

	public List<TokenDefinition> getDefinitions() {
		return tokenDefinitions;
	}

	public TokenDefinition getDefinition(int id) {
		return tokenDefinitions.get(id);
	}

	public TokenDefinition getDefinition(String name) {
		return name2Definition.get(name);
	}
}
