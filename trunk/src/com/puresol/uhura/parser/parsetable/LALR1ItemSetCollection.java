package com.puresol.uhura.parser.parsetable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.DummyTerminal;
import com.puresol.uhura.grammar.production.FinishTerminal;

public class LALR1ItemSetCollection {

	private static final long serialVersionUID = 6813332818983681476L;
	private static final Logger logger = Logger
			.getLogger(LALR1ItemSetCollection.class);

	private final Construction DUMMY_TERMINAL = DummyTerminal.getInstance();

	private final List<LR1ItemSet> itemSetCollection = new ArrayList<LR1ItemSet>();

	private final Grammar grammar;
	private final Closure1 closure1;
	private final LR0ItemSetCollection lr0ItemSetCollection;
	private final LR0StateTransitions lr0Transitions;

	public LALR1ItemSetCollection(Grammar grammar,
			LR0ItemSetCollection lr0ItemSetCollection,
			LR0StateTransitions lr0Transitions, Closure1 closure1)
			throws GrammarException {
		super();
		this.grammar = grammar;
		this.lr0ItemSetCollection = lr0ItemSetCollection;
		this.lr0Transitions = lr0Transitions;
		this.closure1 = closure1;
		calculate();
	}

	private void calculate() throws GrammarException {
		logger.trace("Calculate LR0 kernel items...");
		calculateLR0KernelItemSets();
		logger.trace("Calculate look aheads for " + itemSetCollection.size()
				+ " items...");
		calculateLookahead();
		logger.trace("Build closure items...");
		addClosure();
		logger.trace("Print look ahead table...");
		printLookaheadTable();
	}

	/**
	 * This method takes all item sets from LR0StateTransitionGraph and makes
	 * them to a LR1ItemSet containing only kernel items.
	 */
	private void calculateLR0KernelItemSets() {
		for (int stateId = 0; stateId < lr0ItemSetCollection.getStateNumber(); stateId++) {
			LR0ItemSet lr0ItemSet = lr0ItemSetCollection.getItemSet(stateId);
			Set<LR1Item> lr1Items = new LinkedHashSet<LR1Item>();
			for (LR0Item lr0Item : lr0ItemSet.getKernelItems()) {
				LR1Item lr1Item = new LR1Item(lr0Item.getProduction(),
						lr0Item.getPosition(), DUMMY_TERMINAL);
				lr1Items.add(lr1Item);
			}
			itemSetCollection.add(new LR1ItemSet(lr1Items));
		}
	}

	private void calculateLookahead() {
		boolean changed;
		int run = 0;
		addNewLookahead(0, new LR1Item(grammar.getProductions().get(0), 0,
				FinishTerminal.getInstance()));
		do {
			changed = false;
			run++;
			int currentItemSetCount = itemSetCollection.size();
			for (int stateId = 0; stateId < currentItemSetCount; stateId++) {
				LR1ItemSet lr1ItemSet = itemSetCollection.get(stateId);
				List<LR1Item> kernelItems = new ArrayList<LR1Item>(
						lr1ItemSet.getAllItems());
				for (int kernelItemId = 0; kernelItemId < kernelItems.size(); kernelItemId++) {
					logger.trace("state: " + stateId + " / "
							+ itemSetCollection.size() + "; kernel: "
							+ kernelItemId + " / " + kernelItems.size());

					LR1Item lr1Item = kernelItems.get(kernelItemId);
					LR1ItemSet closure = closure1.calc(lr1Item);
					for (LR1Item closureItem : closure.getAllItems()) {
						if (closureItem.getLookahead().equals(DUMMY_TERMINAL)) {
							continue;
						}
						// Spontaneously generated...
						LR1Item newItem = new LR1Item(
								closureItem.getProduction(),
								closureItem.getPosition() + 1,
								closureItem.getLookahead());
						if (addNewLookahead(stateId, newItem)) {
							changed = true;
						}
					}
				}
			}
		} while (changed);
	}

	private boolean addNewLookahead(int stateId, LR1Item newItem) {
		/*
		 * Look for possible transitions and add stateId to support handling of
		 * finish terminal
		 */
		Set<Integer> possibleTargetStates = new LinkedHashSet<Integer>();
		possibleTargetStates.add(stateId);
		possibleTargetStates.addAll(lr0Transitions.getTransitions(stateId)
				.values());
		boolean added = false;
		for (int setId : possibleTargetStates) {
			LR1ItemSet lr1ItemSet = itemSetCollection.get(setId);
			for (LR1Item lr1Item : lr1ItemSet.getKernelItems()) {
				if ((lr1Item.getPosition() == newItem.getPosition())
						&& lr1Item.getProduction().equals(
								newItem.getProduction())) {
					if (lr1ItemSet.addKernelItem(newItem)) {
						lr1ItemSet.removeItem(new LR1Item(lr1Item
								.getProduction(), lr1Item.getPosition(),
								DUMMY_TERMINAL));
						added = true;
					}
				}
			}
			for (LR1Item lr1Item : lr1ItemSet.getNonKernelItems()) {
				if ((lr1Item.getPosition() == newItem.getPosition())
						&& lr1Item.getProduction().equals(
								newItem.getProduction())) {
					if (lr1ItemSet.addNonKernelItem(newItem)) {
						lr1ItemSet.removeItem(new LR1Item(lr1Item
								.getProduction(), lr1Item.getPosition(),
								DUMMY_TERMINAL));
						added = true;
					}
				}
			}
		}
		return added;
	}

	private void addClosure() {
		for (LR1ItemSet lr1ItemSet : itemSetCollection) {
			lr1ItemSet.addNonKernelItems(closure1.calc(lr1ItemSet)
					.getNonKernelItems());
		}
	}

	public LR1ItemSet getItemSet(int stateId) {
		return itemSetCollection.get(stateId);
	}

	/**
	 * This method looks for the state id for a given item set.
	 * 
	 * @param targetSet
	 * @return
	 * @throws GrammarException
	 */
	public int getStateId(LR1ItemSet targetSet) throws GrammarException {
		for (int stateId = 0; stateId < itemSetCollection.size(); stateId++) {
			LR1ItemSet lr1ItemSet = itemSetCollection.get(stateId);
			if (lr1ItemSet.equals(targetSet)) {
				return stateId;
			}
		}
		throw new GrammarException("Target set '" + targetSet
				+ "' was not found!");
	}

	public int getStateIdForPartialItem(LR1ItemSet targetSet)
			throws GrammarException {
		for (int stateId = 0; stateId < itemSetCollection.size(); stateId++) {
			LR1ItemSet lr1ItemSet = itemSetCollection.get(stateId);
			boolean all = true;
			for (LR1Item lr1Item : targetSet.getKernelItems()) {
				if (!lr1ItemSet.containsItem(lr1Item)) {
					all = false;
					break;
				}
			}
			if (all) {
				return stateId;
			}
		}
		throw new GrammarException("Target set '" + targetSet
				+ "' was not found!");
	}

	public int getStateNumber() {
		return itemSetCollection.size();
	}

	public Grammar getGrammar() {
		return grammar;
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
