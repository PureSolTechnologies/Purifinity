package com.puresol.uhura.parser.parsetable;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class LR1ItemSet extends AbstractItemSet<LR1Item> {

	private static final long serialVersionUID = -4166037691502608458L;

	public LR1ItemSet(AbstractItemSet<LR1Item> itemSet) {
		super(itemSet);
	}

	public LR1ItemSet(LR1Item primaryItem) {
		super(primaryItem);
	}

	public LR1ItemSet(Set<LR1Item> primaryItems) {
		super(primaryItems);
	}

	public boolean equalsCore(LR1ItemSet other) {
		Set<LR1Item> thisSet = new CopyOnWriteArraySet<LR1Item>();
		thisSet.addAll(this.getPrimaryItems());
		thisSet.addAll(this.getAddedItems());

		Set<LR1Item> otherSet = new CopyOnWriteArraySet<LR1Item>();
		otherSet.addAll(other.getPrimaryItems());
		otherSet.addAll(other.getAddedItems());

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
