package com.puresol.uhura.parser.parsetable;

import java.util.Set;

import com.puresol.uhura.grammar.production.DummyTerminal;

public class LR1ItemSet extends AbstractItemSet<LR1Item> {

	private static final long serialVersionUID = -4166037691502608458L;

	public LR1ItemSet(LR1Item primaryItem) {
		super(primaryItem);
	}

	public LR1ItemSet(Set<LR1Item> primaryItems) {
		super(primaryItems);
	}

	public LR1ItemSet(LR1ItemSet initialItemSet) {
		super(initialItemSet);
	}

	public void removeDummies() {
		for (LR1Item lr1Item : getAllItems()) {
			if (lr1Item.getLookahead().equals(DummyTerminal.getInstance())) {
				getAllItems().remove(lr1Item);
				getKernelItems().remove(lr1Item);
				getNonKernelItems().remove(lr1Item);
			}
		}
	}

}
