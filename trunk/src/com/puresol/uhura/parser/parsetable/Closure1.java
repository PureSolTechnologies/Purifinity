package com.puresol.uhura.parser.parsetable;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;

public class Closure1 {

	private final ProductionSet productions;
	private final First first;

	public Closure1(Grammar grammar) {
		this.productions = grammar.getProductions();
		this.first = new First(grammar);
	}

	public LR1ItemSet calc(LR1Item item) {
		return calc(new LR1ItemSet(item));
	}

	/**
	 * This is the closure method for a set of items. This method is descibed in
	 * the Dragon Book 4.6.2.
	 * 
	 * @param initialItemSet
	 * @return A complete set of items is returned containing the parameter
	 *         items and all calculated extensions.
	 */
	public LR1ItemSet calc(LR1ItemSet initialItemSet) {
		LR1ItemSet itemSet = new LR1ItemSet(initialItemSet);
		for (LR1Item item : itemSet.getPrimaryItems()) {
			if (!item.hasNext()) {
				continue;
			}
			Construction nextConstruction = item.getNext();
			if (nextConstruction.isTerminal()) {
				continue;
			}
			Construction secondNextConstruction = item.get2ndNext();
			for (Production subProduction : productions.get(nextConstruction
					.getName())) {
				LR1Item newItem = new LR1Item(subProduction, 0);
				if (secondNextConstruction == null) {
					newItem.addAllLookahead(item.getLookahead());
				} else if (secondNextConstruction.isTerminal()) {
					newItem.addLookahead(secondNextConstruction);
				} else {
					newItem.addAllLookahead(first.get(secondNextConstruction));
				}
				subClosure(itemSet, newItem);
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
	private void subClosure(LR1ItemSet items, LR1Item item) {
		if (items.containsItem(item)) {
			return;
		}
		items.addItem(item);
		if (!item.hasNext()) {
			return;
		}
		Construction nextConstruction = item.getNext();
		if (nextConstruction.isTerminal()) {
			return;
		}
		Construction secondNextConstruction = item.get2ndNext();
		for (Production subProduction : productions.get(nextConstruction
				.getName())) {
			LR1Item newItem = new LR1Item(subProduction, 0);
			if (secondNextConstruction == null) {
				newItem.addAllLookahead(item.getLookahead());
			} else if (secondNextConstruction.isTerminal()) {
				newItem.addLookahead(secondNextConstruction);
			} else {
				newItem.addAllLookahead(first.get(secondNextConstruction));
			}
			subClosure(items, newItem);
		}
	}
}
