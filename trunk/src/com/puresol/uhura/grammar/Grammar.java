package com.puresol.uhura.grammar;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.puresol.uhura.grammar.production.Item;
import com.puresol.uhura.grammar.production.ItemSet;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionElement;
import com.puresol.uhura.grammar.production.ProductionElementType;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.StateTable;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

public class Grammar {

	private TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
	private ProductionSet productions = new ProductionSet();
	private final ConcurrentHashMap<Integer, ConcurrentMap<Integer, Integer>> table = new ConcurrentHashMap<Integer, ConcurrentMap<Integer, Integer>>();

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

	public void caluclateTable() throws GrammarException {
		List<Production> startProductions = productions.get("S");
		if (startProductions.size() == 0) {
			throw new GrammarException("No start production \"S\" was defined!");
		} else if (startProductions.size() > 1) {
			throw new GrammarException(
					"More than one production \"S\" was defined!");
		}
		StateTable stateTable = calculateStateTable(new Item(
				startProductions.get(0), 0));
		System.out.println(stateTable);
	}

	private StateTable calculateStateTable(Item startItem) {
		StateTable table = new StateTable();
		ItemSet set = closure(startItem);
		table.registerItemSet(set);
		return table;
	}

	private ItemSet closure(Item item) {
		ItemSet itemSet = new ItemSet(item);
		Production production = item.getProduction();
		ProductionElement element = production.getElements().get(
				item.getPosition());
		if (element == null) {
			throw new RuntimeException("This should not happen!");
		}
		if (element.getType() == ProductionElementType.PRODUCTION) {
			for (Production subProduction : productions.get(element.getName())) {
				subClosure(itemSet, new Item(subProduction, 0));
			}
		}
		return itemSet;
	}

	private void subClosure(ItemSet items, Item item) {
		if (items.containsItem(item)) {
			return;
		}
		items.addItem(item);
		Production production = item.getProduction();
		ProductionElement element = production.getElements().get(
				item.getPosition());
		if (element == null) {
			throw new RuntimeException("This should not happen!");
		}
		if (element.getType() == ProductionElementType.PRODUCTION) {
			for (Production subProduction : productions.get(element.getName())) {
				subClosure(items, new Item(subProduction, 0));
			}
		}
	}

	public ConcurrentHashMap<Integer, ConcurrentMap<Integer, Integer>> getTable() {
		return table;
	}

	public void printProductions() {
		for (Production production : productions.getProductions()) {
			System.out.println(production);
		}
	}
}
