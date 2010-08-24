package com.puresol.uhura.parser.parsetable;

import java.util.Set;

public class LR0ItemSet extends AbstractItemSet<LR0Item> {

	public LR0ItemSet(AbstractItemSet<LR0Item> itemSet) {
		super(itemSet);
	}

	public LR0ItemSet(LR0Item primaryItem) {
		super(primaryItem);
	}

	public LR0ItemSet(Set<LR0Item> primaryItems) {
		super(primaryItems);
	}

}
