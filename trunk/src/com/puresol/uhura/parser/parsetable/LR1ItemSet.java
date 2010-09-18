package com.puresol.uhura.parser.parsetable;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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

	public boolean equalsCore(LR1ItemSet other) {
		Set<LR1Item> thisSet = new CopyOnWriteArraySet<LR1Item>();
		thisSet.addAll(this.getKernelItems());
		thisSet.addAll(this.getNonKernelItems());

		Set<LR1Item> otherSet = new CopyOnWriteArraySet<LR1Item>();
		otherSet.addAll(other.getKernelItems());
		otherSet.addAll(other.getNonKernelItems());

		if (thisSet.size() != otherSet.size()) {
			return false;
		}

		for (LR1Item thisItem : thisSet) {
			boolean found = false;
			for (LR1Item otherItem : thisSet) {
				if ((thisItem.getPosition() == otherItem.getPosition())
						&& (thisItem.getProduction().equals(otherItem
								.getProduction()))) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

}
