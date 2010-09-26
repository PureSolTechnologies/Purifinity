package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.puresol.uhura.grammar.production.Construction;

public class Goto0 implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ConcurrentMap<LR0ItemSet, ConcurrentMap<Construction, LR0ItemSet>> gotos = new ConcurrentHashMap<LR0ItemSet, ConcurrentMap<Construction, LR0ItemSet>>();

	private final Closure0 closure;

	public Goto0(Closure0 closure) {
		this.closure = closure;
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
		if ((!gotos.containsKey(itemSet))
				|| (!gotos.get(itemSet).containsKey(x))) {
			calculate(itemSet, x);
		}
		return gotos.get(itemSet).get(x);
	}

	private void calculate(LR0ItemSet itemSet, Construction x) {
		Set<LR0Item> items = new LinkedHashSet<LR0Item>();
		for (LR0Item item : itemSet.getNextItems(x)) {
			items.add(new LR0Item(item.getProduction(), item.getPosition() + 1));
		}
		;
		if (!gotos.containsKey(itemSet)) {
			gotos.put(itemSet,
					new ConcurrentHashMap<Construction, LR0ItemSet>());
		}
		gotos.get(itemSet).put(x, closure.calc(new LR0ItemSet(items)));
	}
}
