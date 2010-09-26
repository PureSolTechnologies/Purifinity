package com.puresol.uhura.parser.parsetable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.DummyTerminal;
import com.puresol.uhura.grammar.production.FinishTerminal;

public class LALR1ItemSetCollection extends GeneralStateTransitionGraph {

	private static final long serialVersionUID = 6813332818983681476L;
	private static final Logger logger = Logger
			.getLogger(LALR1ItemSetCollection.class);

	private final ConcurrentMap<LR1ItemSet, Integer> itemSet2Integer = new ConcurrentHashMap<LR1ItemSet, Integer>();
	private final List<LR1ItemSet> itemSetCollection = new ArrayList<LR1ItemSet>();

	private final Grammar grammar;
	private final Closure1 closure1;
	private LR0ItemSetCollection lr0StateTransitionGraph;

	public LALR1ItemSetCollection(Grammar grammar) throws GrammarException {
		super();
		this.grammar = grammar;
		closure1 = new Closure1(grammar, new First(grammar));
		calculate();
	}

	private void calculate() throws GrammarException {
		logger.trace("Calculate LR0 state transition graph...");
		lr0StateTransitionGraph = new LR0ItemSetCollection(grammar);
		logger.trace("Calculate LR0 kernel items...");
		calculateLR0KernelItemSets();
		logger.trace("Calculate look aheads for " + itemSetCollection.size()
				+ " items...");
		calculateLookahead();
		logger.trace("Print look ahead table...");
		printLookaheadTable();
		logger.trace("Add transitions...");
		addTransitions();
	}

	private void calculateLookahead() {
		addNewLookahead(0, new LR1Item(grammar.getProductions().get(0), 0,
				FinishTerminal.getInstance()));
		boolean changed;
		int run = 0;
		do {
			changed = false;
			run++;
			for (int stateId = 0; stateId < itemSetCollection.size(); stateId++) {
				LR1ItemSet lr1ItemSet = itemSetCollection.get(stateId);
				int dummys = getDummyCount();
				List<LR1Item> kernelItems = new ArrayList<LR1Item>(
						lr1ItemSet.getKernelItems());
				for (int kernelItemId = 0; kernelItemId < kernelItems.size(); kernelItemId++) {
					LR1Item lr1Item = kernelItems.get(kernelItemId);
					LR1ItemSet closure = closure1.calc(lr1Item);
					List<LR1Item> closureItems = closure.getAllItems();
					for (int closureItemId = 0; closureItemId < closureItems
							.size(); closureItemId++) {
						// logger.trace("state: " + stateId + "/"
						// + itemSetCollection.size()
						// + " kernelitem: " + kernelItemId + "/"
						// + kernelItems.size()
						// + " closurelitem: " + closureItemId
						// + "/" + closureItems.size() + " run: "
						// + run + " (" + dummys + ")");
						LR1Item closureItem = closureItems.get(closureItemId);
						if (!closureItem.getLookahead().equals(
								DummyTerminal.getInstance())) {
							// Spontaneously generated...
							LR1Item newItem = new LR1Item(
									closureItem.getProduction(),
									closureItem.getPosition() + 1,
									closureItem.getLookahead());
							if (addNewLookahead(stateId, newItem)) {
								changed = true;
								logger.trace("state: " + stateId + "/"
										+ itemSetCollection.size()
										+ " kernelitem: " + kernelItemId + "/"
										+ kernelItems.size()
										+ " closurelitem: " + closureItemId
										+ "/" + closureItems.size() + " run: "
										+ run + " (" + dummys + ")");
							}
						}
					}
				}
			}
			addNewLookahead(0, new LR1Item(grammar.getProductions().get(0), 0,
					FinishTerminal.getInstance()));
		} while (changed);
	}

	private boolean addNewLookahead(int stateId, LR1Item newItem) {
		/*
		 * Look for possible transitions and add stateId to support handling of
		 * finish terminal
		 */
		List<Integer> possibleTargetStates = new ArrayList<Integer>();
		possibleTargetStates.add(stateId);
		possibleTargetStates.addAll(lr0StateTransitionGraph.getTransitions(
				stateId).values());
		boolean added = false;
		for (int setId : possibleTargetStates) {
			LR1ItemSet lr1ItemSet = itemSetCollection.get(setId);
			if (lr1ItemSet.containsItem(newItem)) {
				// if item is already present, skip the set...
				continue;
			}
			Set<LR1Item> lr1Items = new LinkedHashSet<LR1Item>(); // for new
																	// kernel
																	// items
			for (LR1Item lr1Item : lr1ItemSet.getAllItems()) {
				if (lr1Item.getProduction().equals(newItem.getProduction())
						&& (lr1Item.getPosition() == newItem.getPosition())) {
					lr1Items.add(newItem);
					added = true;
					if (!lr1Item.getLookahead().equals(
							DummyTerminal.getInstance())) {
						lr1Items.add(lr1Item);
					}
				} else {
					lr1Items.add(lr1Item);
				}
			}
			itemSetCollection.set(setId, new LR1ItemSet(lr1Items));
		}
		return added;
	}

	/**
	 * This method takes all item sets from LR0StateTransitionGraph and makes
	 * them to a LR1ItemSet containing only kernel items.
	 */
	private void calculateLR0KernelItemSets() {
		for (int stateId = 0; stateId < lr0StateTransitionGraph
				.getStateNumber(); stateId++) {
			LR0ItemSet lr0ItemSet = lr0StateTransitionGraph.getItemSet(stateId);
			Set<LR1Item> lr1Items = new LinkedHashSet<LR1Item>();
			for (LR0Item lr0Item : lr0ItemSet.getKernelItems()) {
				LR1Item lr1Item = new LR1Item(lr0Item.getProduction(),
						lr0Item.getPosition(), DummyTerminal.getInstance());
				lr1Items.add(lr1Item);
			}
			addState(new LR1ItemSet(lr1Items));
		}
	}

	private int addState(LR1ItemSet itemSet) {
		Integer stateId = itemSet2Integer.get(itemSet);
		if (stateId == null) {
			stateId = itemSetCollection.size();
			itemSetCollection.add(itemSet);
			itemSet2Integer.put(itemSet, stateId);
		}
		return stateId;
	}

	private void addTransitions() throws GrammarException {
		for (int stateId = 0; stateId < lr0StateTransitionGraph
				.getStateNumber(); stateId++) {
			for (Construction construction : lr0StateTransitionGraph
					.getTransitions(stateId).keySet()) {
				int targetId = lr0StateTransitionGraph.getTransition(stateId,
						construction);
				addTransition(stateId, construction, targetId);
			}
		}
	}

	public LR1ItemSet getItemSet(int stateId) {
		return itemSetCollection.get(stateId);
	}

	public int getStateNumber() {
		return itemSetCollection.size();
	}

	public Grammar getGrammar() {
		return grammar;
	}

	private int getDummyCount() {
		int count = 0;
		for (LR1ItemSet itemSet : itemSetCollection) {
			for (LR1Item item : itemSet.getAllItems()) {
				if (item.getLookahead().equals(DummyTerminal.getInstance())) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int stateId = 0; stateId < itemSetCollection.size(); stateId++) {
			buffer.append("===========\n");
			buffer.append("State " + stateId + ":\n");
			buffer.append("===========\n");
			LR1ItemSet itemSet = itemSetCollection.get(stateId);
			buffer.append(itemSet.toString());

			ConcurrentMap<Construction, Integer> stateTransitions = getTransitions(stateId);
			if (stateTransitions != null) {
				buffer.append("Transitions:\n");
				for (Construction construction : stateTransitions.keySet()) {
					int finalStateId = stateTransitions.get(construction);
					buffer.append("  ");
					buffer.append(construction.toShortString());
					buffer.append(" --> ");
					buffer.append(finalStateId);
					buffer.append("\n");
				}
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	private void printLookaheadTable() {
		for (int setId = 0; setId < itemSetCollection.size(); setId++) {
			System.out.print(setId);
			LR1ItemSet lr1ItemSet = itemSetCollection.get(setId);
			for (LR1Item lr1Item : lr1ItemSet.getAllItems()) {
				System.out.println("\t" + ((LR0Item) lr1Item).toString());
			}
		}
	}
}
