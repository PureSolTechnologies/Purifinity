package com.puresol.uhura.parser;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.parser.parsetable.Item;
import com.puresol.uhura.parser.parsetable.ItemSet;

public class Closure {

	private final ProductionSet productions;

	public Closure(Grammar grammar) {
		this.productions = grammar.getProductions();
	}

	public ItemSet closure(Item item) {
		Set<Item> set = new CopyOnWriteArraySet<Item>();
		set.add(item);
		return closure(set);
	}

	/**
	 * This is the closure method for a set of items. This method is descibed in
	 * the Dragon Book 4.6.2.
	 * 
	 * @param items
	 * @return A complete set of items is returned containing the parameter
	 *         items and all calculated extensions.
	 */
	public ItemSet closure(Set<Item> items) {
		ItemSet itemSet = new ItemSet(items);
		for (Item item : items) {
			if (!item.hasNext()) {
				continue;
			}
			Construction nextConstruction = item.getNext();
			if (nextConstruction.isTerminal()) {
				continue;
			}
			for (Production subProduction : productions.get(nextConstruction
					.getName())) {
				subClosure(itemSet, new Item(subProduction, 0));
			}
		}
		return itemSet;
	}

	/**
	 * Helper method for closure.
	 * 
	 * @param items
	 * @param item
	 */
	private void subClosure(ItemSet items, Item item) {
		if (items.containsItem(item)) {
			return;
		}
		items.addItem(item);
		if (!item.hasNext()) {
			return;
		}
		Construction nextConstruction = item.getNext();
		if (nextConstruction == null) {
			throw new RuntimeException("This should not happen!");
		}
		if (nextConstruction.isTerminal()) {
			return;
		}
		for (Production subProduction : productions.get(nextConstruction
				.getName())) {
			subClosure(items, new Item(subProduction, 0));
		}
	}
}
