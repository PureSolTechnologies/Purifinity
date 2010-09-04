package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;

public class Goto1 implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Grammar grammar;

	public Goto1(Grammar grammar) {
		this.grammar = grammar;
	}

	/**
	 * This is the closure method for a set of items. This method is descibed in
	 * the Dragon Book 4.6.2.
	 * 
	 * @param items
	 * @return A complete set of items is returned containing the parameter
	 *         items and all calculated extensions.
	 */
	public LR1ItemSet calc(LR1ItemSet itemSet, Construction x) {
		Closure1 closure = new Closure1(grammar);
		Set<LR1Item> items = new CopyOnWriteArraySet<LR1Item>();
		for (LR1Item item : itemSet.getNextItems(x)) {
			LR1Item newItem = new LR1Item(item.getProduction(),
					item.getPosition() + 1);
			newItem.addAllLookahead(item.getLookahead());
			items.add(newItem);
		}
		return closure.calc(new LR1ItemSet(items));
	}
}
