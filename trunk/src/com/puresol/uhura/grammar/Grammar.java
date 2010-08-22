package com.puresol.uhura.grammar;

import java.util.Properties;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

public class Grammar {

	private final Properties options;
	private final TokenDefinitionSet tokenDefinitions;
	private final ProductionSet productions;

	public Grammar(Properties options, TokenDefinitionSet tokenDefinitions,
			ProductionSet productions) throws GrammarException {
		super();
		this.options = options;
		this.tokenDefinitions = tokenDefinitions;
		this.productions = productions;
		checkConsistency();
	}

	private void checkConsistency() throws GrammarException {
		for (Production production : productions.getProductions()) {
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

	public void printTokenDefinitions() {
		for (TokenDefinition definition : tokenDefinitions.getDefinitions()) {
			System.out.println(definition);
		}
	}

	public void printProductions() {
		for (Production production : productions.getProductions()) {
			System.out.println(production.toShortString(-1));
		}
	}
}
