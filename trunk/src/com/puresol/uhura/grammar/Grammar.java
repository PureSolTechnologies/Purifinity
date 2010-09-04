package com.puresol.uhura.grammar;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

public class Grammar implements Serializable {

	private static final long serialVersionUID = 8296461694750760942L;

	private final Properties options;
	private final TokenDefinitionSet tokenDefinitions;
	private final ProductionSet productions;

	public Grammar(Properties options, TokenDefinitionSet tokenDefinitions,
			ProductionSet productions) throws GrammarException {
		super();
		this.options = options;
		this.tokenDefinitions = tokenDefinitions;
		this.productions = productions;
		if (Boolean.valueOf((String) options.get("grammar.checks"))) {
			checkConsistency();
		}
	}

	private void checkConsistency() throws GrammarException {
		for (Production production : productions.getList()) {
			for (Construction construction : production.getConstructions()) {
				if (construction.isTerminal()) {
					if (!construction.getClass()
							.equals(TokenConstruction.class)) {
						continue;
					}
					if (tokenDefinitions.getDefinition(construction.getName()) == null) {
						throw new GrammarException("Token definition '"
								+ construction.getName()
								+ "' used in production '" + production
								+ "'is not defined ");
					}
				} else {
					if (productions.get(construction.getName()) == null) {
						throw new GrammarException("Production '"
								+ construction.getName()
								+ "' used in production '" + production
								+ "'is not defined ");
					}
				}
			}
		}
	}

	/**
	 * @return the options
	 */
	public Properties getOptions() {
		return options;
	}

	/**
	 * @return the tokenDefinitions
	 */
	public TokenDefinitionSet getTokenDefinitions() {
		return tokenDefinitions;
	}

	/**
	 * @return the productions
	 */
	public ProductionSet getProductions() {
		return productions;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("=========\n");
		buffer.append(" Grammar\n");
		buffer.append("=========\n");
		buffer.append("\n");
		buffer.append("Options:\n");
		buffer.append("--------\n");
		buffer.append(toOptionsString());
		buffer.append("\n");
		buffer.append("Tokens:\n");
		buffer.append("-------\n");
		buffer.append(toTokenDefinitionsString());
		buffer.append("\n");
		buffer.append("Productions:\n");
		buffer.append("------------\n");
		buffer.append(toProductionsString());
		buffer.append("\n");
		return buffer.toString();
	}

	public StringBuilder toOptionsString() {
		StringBuilder buffer = new StringBuilder();
		for (Object key : options.keySet()) {
			buffer.append(key + " : " + options.getProperty((String) key)
					+ "\n");
		}
		return buffer;
	}

	public StringBuilder toTokenDefinitionsString() {
		StringBuilder buffer = new StringBuilder();
		for (TokenDefinition definition : tokenDefinitions.getDefinitions()) {
			buffer.append(definition + "\n");
		}
		return buffer;
	}

	public StringBuilder toProductionsString() {
		StringBuilder buffer = new StringBuilder();
		List<Production> productionsList = productions.getList();
		for (Production production : productionsList) {
			buffer.append(production.toShortString(-1) + "\n");
		}
		return buffer;
	}

	public Grammar createWithNewStartProduction(String string)
			throws GrammarException {
		return new Grammar(getOptions(), getTokenDefinitions(),
				getProductions().setNewStartProduction(string));
	}
}
