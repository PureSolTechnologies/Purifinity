package com.puresol.uhura.parser.parsetable;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;

public class Closure0 {

	private final ProductionSet productions;

	public Closure0(ProductionSet productions) {
		this.productions = productions;
	}

	public ItemSet closure(Item item) {
		return closure(new ItemSet(item));
	}

	/**
	 * This is the closure method for a set of items. This method is descibed in
	 * the Dragon Book 4.6.2.
	 * 
	 * @param initialItemSet
	 * @return A complete set of items is returned containing the parameter
	 *         items and all calculated extensions.
	 */
	public ItemSet closure(ItemSet initialItemSet) {
		ItemSet itemSet = new ItemSet(initialItemSet);
		for (Item item : itemSet.getPrimaryItems()) {
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
