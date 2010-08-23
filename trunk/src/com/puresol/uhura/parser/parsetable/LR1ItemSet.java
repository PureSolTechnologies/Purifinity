package com.puresol.uhura.parser.parsetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.production.Construction;

public class LR1ItemSet {

	private final Set<LR1Item> allItems = new CopyOnWriteArraySet<LR1Item>();
	private final Set<LR1Item> primaryItems = new CopyOnWriteArraySet<LR1Item>();
	private final Set<LR1Item> addedItems = new CopyOnWriteArraySet<LR1Item>();

	public LR1ItemSet(LR1Item primaryItem) {
		this.primaryItems.add(primaryItem);
		this.allItems.add(primaryItem);
	}

	public LR1ItemSet(Set<LR1Item> primaryItems) {
		this.primaryItems.addAll(primaryItems);
		this.allItems.addAll(primaryItems);
	}

	public LR1ItemSet(LR1ItemSet itemSet) {
		this.primaryItems.addAll(itemSet.getAllItems());
		this.allItems.addAll(itemSet.getAllItems());
	}

	public boolean containsItem(LR1Item item) {
		if (primaryItems.contains(item)) {
			return true;
		}
		if (addedItems.contains(item)) {
			return true;
		}
		return false;
	}

	public void addAddedItems(Set<LR1Item> items) {
		addedItems.addAll(items);
		allItems.addAll(items);
	}

	public void addItem(LR1Item item) {
		addedItems.add(item);
		allItems.add(item);
	}

	public Set<LR1Item> getAllItems() {
		return allItems;
	}

	public Set<LR1Item> getPrimaryItems() {
		return primaryItems;
	}

	public Set<LR1Item> getAddedItems() {
		return addedItems;
	}

	/**
	 * This method returns all constructions which are next to be expected to be
	 * found.
	 * 
	 * @return
	 */
	public List<Construction> getNextConstructions() {
		List<Construction> constructions = new ArrayList<Construction>();
		for (LR1Item item : primaryItems) {
			Construction element = item.getNext();
			if (element != null) {
				constructions.add(element);
			}
		}
		for (LR1Item item : addedItems) {
			Construction element = item.getNext();
			if (element != null) {
				constructions.add(element);
			}
		}
		return constructions;
	}

	/**
	 * This method returns all items which have the given construction as next
	 * construction.
	 * 
	 * @param construction
	 * @return
	 */
	public List<LR1Item> getNextItems(Construction construction) {
		List<LR1Item> items = new ArrayList<LR1Item>();
		for (LR1Item item : primaryItems) {
			Construction element = item.getNext();
			if (element == null) {
				continue;
			}
			if (element.equals(construction)) {
				items.add(item);
			}
		}
		for (LR1Item item : addedItems) {
			Construction element = item.getNext();
			if (element == null) {
				continue;
			}
			if (element.equals(construction)) {
				items.add(item);
			}
		}
		return items;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (LR1Item item : primaryItems) {
			buffer.append("  ");
			buffer.append(item);
			buffer.append("\n");
		}
		for (LR1Item item : addedItems) {
			buffer.append("+ ");
			buffer.append(item);
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addedItems == null) ? 0 : addedItems.hashCode());
		result = prime * result
				+ ((allItems == null) ? 0 : allItems.hashCode());
		result = prime * result
				+ ((primaryItems == null) ? 0 : primaryItems.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LR1ItemSet other = (LR1ItemSet) obj;
		if (addedItems == null) {
			if (other.addedItems != null)
				return false;
		} else if (!addedItems.equals(other.addedItems))
			return false;
		if (allItems == null) {
			if (other.allItems != null)
				return false;
		} else if (!allItems.equals(other.allItems))
			return false;
		if (primaryItems == null) {
			if (other.primaryItems != null)
				return false;
		} else if (!primaryItems.equals(other.primaryItems))
			return false;
		return true;
	}
}
