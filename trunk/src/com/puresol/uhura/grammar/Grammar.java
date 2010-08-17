package com.puresol.uhura.grammar;

import java.util.Properties;

import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

public class Grammar {

	private Properties options = new Properties();
	private TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
	private ProductionSet productions = new ProductionSet();

	/**
	 * @return the options
	 */
	public Properties getOptions() {
		return options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public void setOptions(Properties options) {
		this.options = options;
	}

	/**
	 * @return the tokenDefinitions
	 */
	public TokenDefinitionSet getTokenDefinitions() {
		return tokenDefinitions;
	}

	/**
	 * @param tokenDefinitions
	 *            the tokenDefinitions to set
	 */
	public void setTokenDefinitions(TokenDefinitionSet tokenDefinitions) {
		this.tokenDefinitions = tokenDefinitions;
	}

	/**
	 * @return the productions
	 */
	public ProductionSet getProductions() {
		return productions;
	}

	/**
	 * @param productions
	 *            the productions to set
	 */
	public void setProductions(ProductionSet productions) {
		this.productions = productions;
	}

	public void printProductions() {
		for (Production production : productions.getProductions()) {
			System.out.println(production);
		}
	}
}
