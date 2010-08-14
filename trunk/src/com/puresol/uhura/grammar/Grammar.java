package com.puresol.uhura.grammar;

import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

public class Grammar {

	private NameReferenceTable nameReference = new NameReferenceTable();
	private TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
	private ProductionSet productions = new ProductionSet();

	/**
	 * @return the tokenNameReference
	 */
	public NameReferenceTable getNameReference() {
		return nameReference;
	}

	/**
	 * @param tokenNameReference
	 *            the tokenNameReference to set
	 */
	public void setNameReference(NameReferenceTable nameReference) {
		this.nameReference = nameReference;
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

}
