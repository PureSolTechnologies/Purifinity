package com.puresol.uhura.parser.parsingtable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.ConstructionType;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TextConstruction;

/**
 * This is a state table for parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParsingTable {

	private final ProductionSet productions;

	private int itemSetCounter = 0;
	private final ConcurrentMap<Integer, ItemSet> itemSets = new ConcurrentHashMap<Integer, ItemSet>();

	private final ConcurrentMap<Integer, Integer> backwardTransitions = new ConcurrentHashMap<Integer, Integer>();

	private final Set<Construction> availableTerminals = new HashSet<Construction>();
	private final Set<Construction> availableNonTerminals = new HashSet<Construction>();
	private final ConcurrentHashMap<Integer, ConcurrentMap<Construction, ParserAction>> table = new ConcurrentHashMap<Integer, ConcurrentMap<Construction, ParserAction>>();

	public ParsingTable(Grammar grammar) throws GrammarException {
		super();
		this.productions = grammar.getProductions();
		caluclateParsingTable();
	}

	/**
	 * This class is used to register a new itemSet and to provide it with a new
	 * item set number. Furthermore, all tables and hashes are prepared to take
	 * some actions for the item set.
	 * 
	 * @param itemSet
	 * @return
	 */
	private int registerItemSet(ItemSet itemSet) {
		int result = itemSetCounter;
		if (itemSets.values().contains(itemSet)) {
			for (int i = 0; i < itemSets.size(); i++) {
				ItemSet includedItemSet = itemSets.get(i);
				if (includedItemSet.equals(itemSet)) {
					return i;
				}
			}
			return result;
		}
		itemSets.put(itemSetCounter, itemSet);
		table.put(itemSetCounter,
				new ConcurrentHashMap<Construction, ParserAction>());
		itemSetCounter++;
		return result;
	}

	private void registerActionAndBackwardTransition(int initialState,
			Construction construction, ActionType action, int targetState)
			throws GrammarException {
		ParserAction tableActionEntry = new ParserAction(action,
				targetState);
		registerAction(initialState, construction, tableActionEntry);
		registerBackwardTransition(initialState, construction, targetState);
	}

	private void registerAction(int initialState, Construction construction,
			ParserAction action) throws GrammarException {
		ConcurrentMap<Construction, ParserAction> row = table
				.get(initialState);
		if (row.containsKey(construction)) {
			if (!row.get(construction).equals(action)) {
				throw new GrammarException("Ambiguous condition in state '"
						+ initialState + "' for consruction '" + construction
						+ "': " + row.get(construction) + " <--> " + action);
			}
		} else {
			row.put(construction, action);
		}
		if (construction.isNonTerminal()) {
			if (!availableNonTerminals.contains(construction)) {
				availableNonTerminals.add(construction);
			}
		} else {
			if (!availableTerminals.contains(construction)) {
				availableTerminals.add(construction);
			}
		}
	}

	private void registerBackwardTransition(int initialState,
			Construction construction, int targetState) {
		backwardTransitions.put(targetState, initialState);
	}

	private ParserAction getAction(int initialState,
			Construction construction) {
		ParserAction action = table.get(initialState).get(construction);
		if (action == null) {
			return new ParserAction(ActionType.ERROR, -1);
		}
		return action;
	}

	/**
	 * This private method is called by the constructor to calculate the whole
	 * state table.
	 * 
	 * @throws GrammarException
	 */
	private void caluclateParsingTable() throws GrammarException {
		calculateTransitions();
		calculateReductionsAndAcceptance();
	}

	private void calculateTransitions() throws GrammarException {
		List<Production> startProductions = productions.get("S");
		if (startProductions.size() == 0) {
			throw new GrammarException("No start production \"S\" was defined!");
		} else if (startProductions.size() > 1) {
			throw new GrammarException(
					"More than one production \"S\" was defined!");
		}
		Set<Item> items = new HashSet<Item>();
		items.add(new Item(startProductions.get(0), 0));
		calculateItemSet(items, 0);
	}

	private int calculateItemSet(Set<Item> items, int previousStateId)
			throws GrammarException {
		ItemSet initialItemSet = closure(items);
		int initialStateId = registerItemSet(initialItemSet);

		for (Construction construction : initialItemSet.getFollowing()) {
			Set<Item> rightMovedItems = new HashSet<Item>();
			for (Item item : initialItemSet.getFollowingItems(construction)) {
				Item rightMovedItem = new Item(item.getProduction(),
						item.getPosition() + 1);
				rightMovedItems.add(rightMovedItem);
			}
			int targetStateId = calculateItemSet(rightMovedItems,
					initialStateId);
			if (construction.isTerminal()) {
				registerActionAndBackwardTransition(initialStateId,
						construction, ActionType.SHIFT, targetStateId);
			} else if (construction.isNonTerminal()) {
				registerActionAndBackwardTransition(initialStateId,
						construction, ActionType.GOTO, targetStateId);
			}
		}
		return initialStateId;
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
			List<Construction> constructions = item.getProduction()
					.getConstructions();
			if (item.getPosition() >= constructions.size()) {
				continue;
			}
			Construction element = constructions.get(item.getPosition());
			if (element.isNonTerminal()) {
				for (Production subProduction : productions.get(element
						.getName())) {
					subClosure(itemSet, new Item(subProduction, 0));
				}
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

	private void calculateReductionsAndAcceptance() throws GrammarException {
		availableTerminals.add(new TextConstruction("$"));
		Production startProduction = productions.get("S").get(0);
		Item accItem = new Item(startProduction, 1);
		for (Integer state : itemSets.keySet()) {
			ItemSet itemSet = itemSets.get(state);
			if (itemSet.containsItem(accItem)) {
				registerAction(state, new TextConstruction("$"),
						new ParserAction(ActionType.ACCEPT, -1));
			}
			boolean reduced = false;
			for (Item item : itemSet.getPrimaryItems()) {
				if ((!item.hasNext()) && (!item.equals(accItem))) {
					for (Construction construction : availableTerminals) {
						registerAction(state, construction,
								new ParserAction(ActionType.REDUCE,
										(int) backwardTransitions.get(state)));
					}
					reduced = true;
				}
			}
			if (!reduced) {
				for (Item item : itemSet.getPrimaryItems()) {
					if ((!item.hasNext()) && (!item.equals(accItem))) {
						reduced = true;
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("===========\n");
		buffer.append("Item States\n");
		buffer.append("===========\n");
		buffer.append("\n");
		for (int i = 0; i < itemSetCounter; i++) {
			buffer.append("State ");
			buffer.append(i);
			buffer.append(":\n");
			buffer.append(itemSets.get(i).toString());
			buffer.append("\n");
		}
		buffer.append("=============\n");
		buffer.append("Parsing Table\n");
		buffer.append("=============\n");
		buffer.append("\n");
		buffer.append(toColumn("STATE: |"));
		boolean first = true;
		for (int i = 0; i < availableTerminals.size(); i++) {
			if (first) {
				buffer.append(toColumn("ACTION:"));
				first = false;
			} else {
				buffer.append(toColumn(" "));
			}
		}
		buffer.append(toColumn("|"));
		first = true;
		for (int i = 0; i < availableNonTerminals.size(); i++) {
			if (first) {
				buffer.append(toColumn("GOTO:"));
				first = false;
			} else {
				buffer.append(toColumn(" "));
			}
		}
		buffer.append("\n");
		buffer.append(toColumn("|"));
		for (Construction construction : availableTerminals) {
			buffer.append(toColumn(construction.getText()));
		}
		buffer.append(toColumn("|"));
		for (Construction construction : availableNonTerminals) {
			buffer.append(toColumn(construction.getName()));
		}
		buffer.append("\n");
		buffer.append(toColumn("-------|"));
		for (int i = 0; i < availableTerminals.size(); i++) {
			buffer.append(toColumn("--------"));
		}
		buffer.append(toColumn("-------|"));
		for (int i = 0; i < availableNonTerminals.size(); i++) {
			buffer.append(toColumn("--------"));
		}
		buffer.append("\n");
		for (Integer state : table.keySet()) {
			buffer.append(toColumn(String.valueOf(state) + " |"));
			for (Construction construction : availableTerminals) {
				buffer.append(toColumn(getAction(state, construction)
						.toString()));
			}
			buffer.append(toColumn("|"));
			for (Construction construction : availableNonTerminals) {
				buffer.append(toColumn(getAction(state, construction)
						.toString()));
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	private String toColumn(String s) {
		StringBuffer result = new StringBuffer();
		while (result.length() + s.length() < 8) {
			result.append(' ');
		}
		result.append(s);
		return result.toString();
	}
}
