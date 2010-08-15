package com.puresol.uhura.grammar.production;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
}
