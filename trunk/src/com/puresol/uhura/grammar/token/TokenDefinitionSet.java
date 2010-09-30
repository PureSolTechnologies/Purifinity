package com.puresol.uhura.grammar.token;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.uhura.grammar.GrammarException;

/**
 * A token definitions set is meant to be the total collection of all token
 * definitions defined within a single grammar.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TokenDefinitionSet implements Serializable {

	private static final long serialVersionUID = 8379268661591883917L;

	private final Map<String, TokenDefinition> name2Definition = new HashMap<String, TokenDefinition>();
	private final List<TokenDefinition> tokenDefinitions = new ArrayList<TokenDefinition>();

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
