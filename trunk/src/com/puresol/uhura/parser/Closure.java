package com.puresol.uhura.parser;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.parser.parsetable.Item;
import com.puresol.uhura.parser.parsetable.ItemSet;

public class Closure {

	// private int itemSetCounter = 0;
	// private final ConcurrentMap<Integer, ItemSet> itemSets = new
	// ConcurrentHashMap<Integer, ItemSet>();

	private final ProductionSet productions;

	public Closure(Grammar grammar) {
		this.productions = grammar.getProductions();
		// calculateClosures();
	}

	/**
	 * This class is used to register a new itemSet and to provide it with a new
	 * item set number. Furthermore, all tables and hashes are prepared to take
	 * some actions for the item set.
	 * 
	 * @param itemSet
	 * @return
	 */
	// private void registerItemSet(ItemSet itemSet) {
	// System.out.println(itemSet.toString());
	// if (itemSets.values().contains(itemSet)) {
	// for (int i = 0; i < itemSets.size(); i++) {
	// ItemSet includedItemSet = itemSets.get(i);
	// if (includedItemSet.equals(itemSet)) {
	// return;
	// }
	// }
	// return;
	// }
	// itemSets.put(itemSetCounter, itemSet);
	// itemSetCounter++;
	// }

	// private void calculateClosures() {
	// Set<Item> items = new CopyOnWriteArraySet<Item>();
	// items.add(new Item(productions.getProductions().iterator().next(), 0));
	// calculateClosureRecursively(items);
	// }

	public void calculateClosureRecursively(Set<Item> items) {
		ItemSet initialItemSet = closure(items);
		// registerItemSet(initialItemSet);

		for (Construction construction : initialItemSet.getNextConstructions()) {
			Set<Item> rightMovedItems = new CopyOnWriteArraySet<Item>();
			for (Item item : initialItemSet.getNextItems(construction)) {
				Item rightMovedItem = new Item(item.getProduction(),
						item.getPosition() + 1);
				rightMovedItems.add(rightMovedItem);
			}
			calculateClosureRecursively(rightMovedItems);
		}
	}

	/**
	 * This is the closure method for a set of items. This method is descibed in
	 * the Dragon Book 4.6.2.
	 * 
	 * @param items
	 * @return A complete set of items is returned containing the parameter
	 *         items and all calculated extensions.
	 */
	private ItemSet closure(Set<Item> items) {
		ItemSet itemSet = new ItemSet(items);
		for (Item item : items) {
			if (!item.hasNext()) {
				continue;
			}
			Construction nextConstruction = item.getNext();
			if (nextConstruction.isTerminal()) {
				continue;
			}
			for (Production subProduction : productions.get(nextConstruction
					.getName())) {
				subClosure(itemSet, new Item(subProduction, 0));
			}
		}
		return itemSet;
	}

	/**
	 * Helper method for closure.
	 * 
	 * @param items
	 * @param item
	 */
	private void subClosure(ItemSet items, Item item) {
		if (items.containsItem(item)) {
			return;
		}
		items.addItem(item);
		if (!item.hasNext()) {
			return;
		}
		Construction nextConstruction = item.getNext();
		if (nextConstruction == null) {
			throw new RuntimeException("This should not happen!");
		}
		if (nextConstruction.isTerminal()) {
			return;
		}
		for (Production subProduction : productions.get(nextConstruction
				.getName())) {
			subClosure(items, new Item(subProduction, 0));
		}
	}

	// public ItemSet getState(int state) {
	// return itemSets.get(state);
	// }

	// public ConcurrentMap<Integer, ItemSet> getAllStates() {
	// return itemSets;
	// }
}
