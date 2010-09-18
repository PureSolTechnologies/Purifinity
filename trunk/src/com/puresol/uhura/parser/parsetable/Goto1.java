package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;

public class Goto1 implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Closure1 closure;

	public Goto1(Grammar grammar) {
		this.closure = new Closure1(grammar);
	}

	public Goto1(Closure1 closure) {
		this.closure = closure;
	}

	/**
	 * This is the closure method for a set of items. This method is described
	 * in the Dragon Book 4.6.2.
	 * 
	 * <pre>
	 * SetOfItems GOTO(I, X) {
	 * 		initialisiere J als die leere Menge;
	 * 		for ( jedes Item [ A --> alpha . X beta, a ] in I )
	 * 			fuege Item [A --> alpha X . beta, a] zur Menge J hinzu'
	 * 		return closure(J);
	 * }
	 * </pre>
	 * 
	 * @param items
	 * @return A complete set of items is returned containing the parameter
	 *         items and all calculated extensions.
	 */
	public LR1ItemSet calc(LR1ItemSet itemSet, Construction x) {
		Set<LR1Item> items = new LinkedHashSet<LR1Item>();
		for (LR1Item item : itemSet.getNextItems(x)) {
			LR1Item newItem = new LR1Item(item.getProduction(),
					item.getPosition() + 1, item.getLookahead());
			items.add(newItem);
		}
		return closure.calc(new LR1ItemSet(items));
	}
}
