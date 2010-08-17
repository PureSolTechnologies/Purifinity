package com.puresol.uhura.parser.statetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.production.Construction;

public class ItemSet {

	private final Set<Item> primaryItems = new CopyOnWriteArraySet<Item>();
	private final Set<Item> addedItems = new CopyOnWriteArraySet<Item>();

	public ItemSet(Item primaryItem) {
		this.primaryItems.add(primaryItem);
	}

	public ItemSet(Set<Item> primaryItems) {
		this.primaryItems.addAll(primaryItems);
	}

	public boolean containsItem(Item item) {
		if (primaryItems.contains(item)) {
			return true;
		}
		if (addedItems.contains(item)) {
			return true;
		}
		return false;
	}

	public void addAddedItems(Set<Item> items) {
		addedItems.addAll(items);
	}

	public void addItem(Item item) {
		addedItems.add(item);
	}

	public Set<Item> getPrimaryItems() {
		return primaryItems;
	}

	public Set<Item> getAddedItems() {
		return addedItems;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Item item : primaryItems) {
			buffer.append("  ");
			buffer.append(item);
			buffer.append("\n");
		}
		for (Item item : addedItems) {
			buffer.append("+ ");
			buffer.append(item);
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public List<Construction> getFollowing() {
		List<Construction> terminals = new ArrayList<Construction>();
		for (Item item : primaryItems) {
			Construction element = item.getNext();
			if (element != null) {
				terminals.add(element);
			}
		}
		for (Item item : addedItems) {
			Construction element = item.getNext();
			if (element != null) {
				terminals.add(element);
			}
		}
		return terminals;
	}

	public List<Item> getFollowingItems(Construction construction) {
		List<Item> items = new ArrayList<Item>();
		for (Item item : primaryItems) {
			Construction element = item.getNext();
			if (element == null) {
				continue;
			}
			if (element.equals(construction)) {
				items.add(item);
			}
		}
		for (Item item : addedItems) {
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ItemSet other = (ItemSet) obj;
		if (addedItems == null) {
			if (other.addedItems != null) {
				return false;
			}
		} else if (!addedItems.equals(other.addedItems)) {
			return false;
		}
		if (primaryItems == null) {
			if (other.primaryItems != null) {
				return false;
			}
		} else if (!primaryItems.equals(other.primaryItems)) {
			return false;
		}
		return true;
	}

}
