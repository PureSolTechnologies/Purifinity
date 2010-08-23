package com.puresol.uhura.parser.parsetable;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.ProductionSet;

public class Goto0 {

	private final ProductionSet productions;

	public Goto0(ProductionSet productions) {
		this.productions = productions;
	}

	/**
	 * This is the closure method for a set of items. This method is descibed in
	 * the Dragon Book 4.6.2.
	 * 
	 * @param items
	 * @return A complete set of items is returned containing the parameter
	 *         items and all calculated extensions.
	 */
	public ItemSet goto0(ItemSet itemSet, Construction x) {
		Closure0 closure = new Closure0(productions);
		Set<Item> items = new CopyOnWriteArraySet<Item>();
		for (Item item : itemSet.getNextItems(x)) {
			items.add(new Item(item.getProduction(), item.getPosition() + 1));
		}
		return closure.closure(new ItemSet(items));
	}
}
