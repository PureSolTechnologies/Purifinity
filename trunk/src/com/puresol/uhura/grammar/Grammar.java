package com.puresol.uhura.grammar;

import java.util.List;
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

	public void println() {
		System.out.println("=========");
		System.out.println(" Grammar");
		System.out.println("=========");
		System.out.println();
		System.out.println("Options:");
		System.out.println("--------");
		printOptions();
		System.out.println();
		System.out.println("Tokens:");
		System.out.println("-------");
		printTokenDefinitions();
		System.out.println();
		System.out.println("Productions:");
		System.out.println("------------");
		printProductions();
		System.out.println();
	}

	public void printOptions() {
		for (Object key : options.keySet()) {
			System.out.println(key + " = " + options.getProperty((String) key));
		}
	}

	public void printTokenDefinitions() {
		for (TokenDefinition definition : tokenDefinitions.getDefinitions()) {
			System.out.println(definition);
		}
	}

	public void printProductions() {
		List<Production> productionsList = productions.getProductions();
		for (int i = 0; i < productionsList.size(); i++) {
			System.out.print("(");
			System.out.print(i);
			System.out.print(")\t");
			Production production = productionsList.get(i);
			System.out.println(production.toShortString(-1));
		}
	}
}
