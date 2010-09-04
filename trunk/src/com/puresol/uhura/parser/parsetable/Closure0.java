package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;

/**
 * The rules from Dragon Book are:
 * 
 * 1) Fuege zuerst jedes Item von I in CLOSURE(I) ein.
 * 
 * 2) Wenn A --> alpha . B beta in CLOSURE(I) und B --> gamma eine Produktion
 * ist, fuege das Item B --> . gamma in CLOSURE(I) ein, falls es noch nicht
 * vorhanden ist. Wende diese Regel an, bis keine weiteren Items mehr in
 * CLOSURE(I) eingefuegt werden koennen.
 * 
 * Pseudo Algorithm:
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Closure0 implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ProductionSet productions;

	public Closure0(Grammar grammar) {
		this.productions = grammar.getProductions();
	}

	public LR0ItemSet calc(LR0Item item) {
		return calc(new LR0ItemSet(item));
	}

	/**
	 * This is the closure method for a set of items. This method is described
	 * in the Dragon Book 4.6.2.
	 * 
	 * @param initialItemSet
	 * @return A complete set of items is returned containing the parameter
	 *         items and all calculated extensions.
	 */
	public LR0ItemSet calc(LR0ItemSet initialItemSet) {
		LR0ItemSet itemSet = new LR0ItemSet(initialItemSet);
		for (LR0Item item : itemSet.getPrimaryItems()) {
			if (!item.hasNext()) {
				continue;
			}
			Construction nextConstruction = item.getNext();
			if (nextConstruction.isTerminal()) {
				continue;
			}
			for (Production subProduction : productions.get(nextConstruction
					.getName())) {
				subClosure(itemSet, new LR0Item(subProduction, 0));
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
	private void subClosure(LR0ItemSet items, LR0Item item) {
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
		for (Production subProduction : productions.get(nextConstruction
				.getName())) {
			subClosure(items, new LR0Item(subProduction, 0));
		}
	}
}
