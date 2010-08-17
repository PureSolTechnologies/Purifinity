package com.puresol.uhura.grammar.production;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;

public class StateTable {

	private final Grammar grammar;
	private final ProductionSet productions;

	private int stateCounter = 0;
	private final Set<Item> primaryItems = new CopyOnWriteArraySet<Item>();
	private final Set<ItemSet> itemSets = new CopyOnWriteArraySet<ItemSet>();
	private final ConcurrentMap<Integer, ItemSet> states = new ConcurrentHashMap<Integer, ItemSet>();

	private final ConcurrentHashMap<Integer, ConcurrentMap<Integer, Integer>> table = new ConcurrentHashMap<Integer, ConcurrentMap<Integer, Integer>>();

	public StateTable(Grammar grammar) throws GrammarException {
		super();
		this.grammar = grammar;
		this.productions = grammar.getProductions();
		caluclateStateTable();
	}

	/**
	 * @return the grammar
	 */
	public Grammar getGrammar() {
		return grammar;
	}

	public int registerItemSet(ItemSet itemSet) {
		int result = stateCounter;
		if (itemSets.contains(itemSet)) {
			return result;
		}
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

	public void caluclateStateTable() throws GrammarException {
		List<Production> startProductions = productions.get("S");
		if (startProductions.size() == 0) {
			throw new GrammarException("No start production \"S\" was defined!");
		} else if (startProductions.size() > 1) {
			throw new GrammarException(
					"More than one production \"S\" was defined!");
		}
		Set<Item> items = new HashSet<Item>();
		items.add(new Item(startProductions.get(0), 0));
		calculateState(items);
	}

	private void calculateState(Set<Item> item) {
		ItemSet itemSet = closure(item);
		registerItemSet(itemSet);
		System.out.println(itemSet.toString());
		for (Construction construction : itemSet.getFollowing()) {
			Set<Item> items = new HashSet<Item>();
			for (Item originItem : itemSet.getFollowingItems(construction)) {
				Item clonedItem = new Item(originItem.getProduction(),
						originItem.getPosition() + 1);
				items.add(clonedItem);
			}
			calculateState(items);
		}
	}

	private ItemSet closure(Set<Item> items) {
		ItemSet itemSet = new ItemSet(items);
		for (Item item : items) {
			Production production = item.getProduction();
			List<Construction> constructions = production.getConstructions();
			if (item.getPosition() >= constructions.size()) {
				continue;
			}
			Construction element = constructions.get(item.getPosition());
			if (element == null) {
				throw new RuntimeException("This should not happen!");
			}
			if (element.getType() == ConstructionType.PRODUCTION) {
				for (Production subProduction : productions.get(element
						.getName())) {
					subClosure(itemSet, new Item(subProduction, 0));
				}
			}
		}
		return itemSet;
	}

	private void subClosure(ItemSet items, Item item) {
		if (items.containsItem(item)) {
			return;
		}
		items.addItem(item);
		Production production = item.getProduction();
		Construction element = production.getConstructions().get(
				item.getPosition());
		if (element == null) {
			throw new RuntimeException("This should not happen!");
		}
		if (element.getType() == ConstructionType.PRODUCTION) {
			for (Production subProduction : productions.get(element.getName())) {
				subClosure(items, new Item(subProduction, 0));
			}
		}
	}

	public ConcurrentHashMap<Integer, ConcurrentMap<Integer, Integer>> getTable() {
		return table;
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
