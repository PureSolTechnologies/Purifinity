package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;

public class Goto0 implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Grammar grammar;

	public Goto0(Grammar grammar) {
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
	public LR0ItemSet calc(LR0ItemSet itemSet, Construction x) {
		Closure0 closure = new Closure0(grammar);
		Set<LR0Item> items = new LinkedHashSet<LR0Item>();
		for (LR0Item item : itemSet.getNextItems(x)) {
			items.add(new LR0Item(item.getProduction(), item.getPosition() + 1));
		}
		return closure.calc(new LR0ItemSet(items));
	}
}
