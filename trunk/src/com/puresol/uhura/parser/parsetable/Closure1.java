package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;

public class Closure1 implements Serializable {

	private static final long serialVersionUID = 5779840433746397739L;

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
	 * <pre>
	 * 	SetOfItems CLOSURE(I) {
	 * 		repeat
	 * 			for (jedes Item [ A --> alpha . B beta, a] in I)
	 * 				for (jede Produktion B --> gamma in G')
	 * 					for ( jedes Terminal b in FIRST(beta a) )
	 * 						fuege [ B --> . gamma, b ] zur Menge I hinzu;
	 * 		until es werden keine weiteren Items mehr zu I hinzugefuegt;
	 * 	}
	 * </pre>
	 * 
	 * @param initialItemSet
	 * @return A complete set of items is returned containing the parameter
	 *         items and all calculated extensions.
	 */
	public LR1ItemSet calc(LR1ItemSet initialItemSet) {
		LR1ItemSet itemSet = new LR1ItemSet(initialItemSet);
		boolean changed;
		do {
			changed = false;
			for (LR1Item item : itemSet.getAllItems()) {
				if (!item.hasNext()) {
					continue;
				}
				Construction nextConstruction = item.getNext();
				if (nextConstruction.isTerminal()) {
					continue;
				}
				for (Production subProduction : productions
						.get(nextConstruction.getName())) {
					Production helperProduction = getRemainingProduction(item,
							item.getLookahead());
					for (Construction terminal : first.get(helperProduction)) {
						LR1Item newItem = new LR1Item(subProduction, 0,
								terminal);
						if (!itemSet.containsItem(newItem)) {
							itemSet.addItem(newItem);
							changed = true;
						}
					}
				}
			}
		} while (changed);
		return itemSet;
	}

	private Production getRemainingProduction(LR1Item item,
			Construction lookAhead) {
		Production production = new Production(item.getProduction().getName());
		for (int id = item.getPosition() + 1; id < item.getProduction()
				.getConstructions().size(); id++) {
			production.addConstruction(item.getProduction().getConstructions()
					.get(id));
		}
		production.addConstruction(lookAhead);
		return production;
	}
}
