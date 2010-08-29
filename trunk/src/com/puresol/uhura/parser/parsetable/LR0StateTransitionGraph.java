package com.puresol.uhura.parser.parsetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;

public class LR0StateTransitionGraph {

	private final ConcurrentMap<LR0ItemSet, Integer> itemSet2Integer = new ConcurrentHashMap<LR0ItemSet, Integer>();
	private final ConcurrentMap<Integer, LR0ItemSet> itemSets = new ConcurrentHashMap<Integer, LR0ItemSet>();
	private final ConcurrentMap<Integer, ConcurrentMap<Construction, Integer>> transitions = new ConcurrentHashMap<Integer, ConcurrentMap<Construction, Integer>>();

	private final Grammar grammar;
	private final Closure0 closureCalculator;
	private final Goto0 gotoCalculator;

	public LR0StateTransitionGraph(Grammar grammar) {
		super();
		this.grammar = grammar;
		closureCalculator = new Closure0(grammar);
		gotoCalculator = new Goto0(grammar);
		calculate();
	}

	private void calculate() {
		LR0Item startItem = new LR0Item(grammar.getProductions().get(0), 0);
		LR0ItemSet initialSet = closureCalculator
				.calc(new LR0ItemSet(startItem));
		addState(initialSet);
		calculateRecursively(initialSet);
	}

	private void calculateRecursively(LR0ItemSet initialSet) {
		int initialStateId = itemSet2Integer.get(initialSet);
		for (Construction construction : initialSet.getNextConstructions()) {
			LR0ItemSet gotoSet = gotoCalculator.calc(initialSet, construction);
			if (!itemSets.containsValue(gotoSet)) {
				int finalStateId = addState(gotoSet);
				addTransition(initialStateId, construction, finalStateId);
				calculateRecursively(gotoSet);
			} else {
				int finalStateId = itemSet2Integer.get(gotoSet);
				addTransition(initialStateId, construction, finalStateId);
			}
		}
	}

	private int addState(LR0ItemSet itemSet) {
		if (itemSet2Integer.containsKey(itemSet)) {
			return itemSet2Integer.get(itemSet);
		}
		int stateId = itemSets.size();
		itemSets.put(stateId, itemSet);
		itemSet2Integer.put(itemSet, stateId);
		return stateId;
	}

	private void addTransition(int initialState, Construction construction,
			int finalState) {
		if (!transitions.containsKey(initialState)) {
			transitions.put(initialState,
					new ConcurrentHashMap<Construction, Integer>());
		}
		transitions.get(initialState).put(construction, finalState);
	}

	public LR0ItemSet getItemSet(int stateId) {
		return itemSets.get(stateId);
	}

	public int getStateNumber() {
		return itemSets.size();
	}

	public ConcurrentMap<Construction, Integer> getTransitions(int initialState) {
		ConcurrentMap<Construction, Integer> result = transitions
				.get(initialState);
		if (result == null) {
			result = new ConcurrentHashMap<Construction, Integer>();
		}
		return result;
	}

	public int getTransition(int initialState, Construction construction) {
		return transitions.get(initialState).get(construction);
	}

	public Grammar getGrammar() {
		return grammar;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		List<Integer> stateIds = new ArrayList<Integer>(itemSets.keySet());
		Collections.sort(stateIds);
		for (int stateId : stateIds) {
			buffer.append("===========\n");
			buffer.append("State " + stateId + ":\n");
			buffer.append("===========\n");
			LR0ItemSet itemSet = itemSets.get(stateId);
			buffer.append(itemSet.toString());

			ConcurrentMap<Construction, Integer> stateTransitions = transitions
					.get(stateId);
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
}
