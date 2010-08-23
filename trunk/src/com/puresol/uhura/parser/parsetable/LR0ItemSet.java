package com.puresol.uhura.parser.parsetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.production.Construction;

public class LR0ItemSet {

	private final Set<LR0Item> allItems = new CopyOnWriteArraySet<LR0Item>();
	private final Set<LR0Item> primaryItems = new CopyOnWriteArraySet<LR0Item>();
	private final Set<LR0Item> addedItems = new CopyOnWriteArraySet<LR0Item>();

	public LR0ItemSet(LR0Item primaryItem) {
		this.primaryItems.add(primaryItem);
		this.allItems.add(primaryItem);
	}

	public LR0ItemSet(Set<LR0Item> primaryItems) {
		this.primaryItems.addAll(primaryItems);
		this.allItems.addAll(primaryItems);
	}

	public LR0ItemSet(LR0ItemSet itemSet) {
		this.primaryItems.addAll(itemSet.getAllItems());
		this.allItems.addAll(itemSet.getAllItems());
	}

	public boolean containsItem(LR0Item item) {
		if (primaryItems.contains(item)) {
			return true;
		}
		if (addedItems.contains(item)) {
			return true;
		}
		return false;
	}

	public void addAddedItems(Set<LR0Item> items) {
		addedItems.addAll(items);
		allItems.addAll(items);
	}

	public void addItem(LR0Item item) {
		addedItems.add(item);
		allItems.add(item);
	}

	public Set<LR0Item> getAllItems() {
		return allItems;
	}

	public Set<LR0Item> getPrimaryItems() {
		return primaryItems;
	}

	public Set<LR0Item> getAddedItems() {
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
		for (LR0Item item : primaryItems) {
			Construction element = item.getNext();
			if (element != null) {
				constructions.add(element);
			}
		}
		for (LR0Item item : addedItems) {
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
	public List<LR0Item> getNextItems(Construction construction) {
		List<LR0Item> items = new ArrayList<LR0Item>();
		for (LR0Item item : primaryItems) {
			Construction element = item.getNext();
			if (element == null) {
				continue;
			}
			if (element.equals(construction)) {
				items.add(item);
			}
		}
		for (LR0Item item : addedItems) {
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
		for (LR0Item item : primaryItems) {
			buffer.append("  ");
			buffer.append(item);
			buffer.append("\n");
		}
		for (LR0Item item : addedItems) {
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
				+ ((allItems == null) ? 0 : allItems.hashCode());
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
		LR0ItemSet other = (LR0ItemSet) obj;
		if (allItems == null) {
			if (other.allItems != null)
				return false;
		} else if (!allItems.equals(other.allItems))
			return false;
		return true;
	}

}
