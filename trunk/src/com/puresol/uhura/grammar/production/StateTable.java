package com.puresol.uhura.grammar.production;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class StateTable {

	private int stateCounter = 0;
	private final Set<Item> primaryItems = new CopyOnWriteArraySet<Item>();
	private final Set<ItemSet> itemSets = new CopyOnWriteArraySet<ItemSet>();
	private final ConcurrentMap<Integer, ItemSet> states = new ConcurrentHashMap<Integer, ItemSet>();

	public int registerItemSet(ItemSet itemSet) {
		int result = stateCounter;
		itemSets.add(itemSet);
		primaryItems.addAll(itemSet.getPrimaryItems());
		states.put(stateCounter, itemSet);
		stateCounter++;
		return result;
	}

	public boolean isStatePresent(Item item) {
		return primaryItems.contains(item);
	}

	public ItemSet getItemSet(int id) {
		return states.get(id);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < stateCounter; i++) {
			buffer.append("State ");
			buffer.append(i);
			buffer.append(":\n");
			buffer.append(states.get(i).toString());
		}
		return buffer.toString();
	}
}
