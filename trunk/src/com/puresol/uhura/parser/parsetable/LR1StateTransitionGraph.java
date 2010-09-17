package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;

public class LR1StateTransitionGraph implements Serializable {

	private static final long serialVersionUID = -1330346621768260912L;

	private final static Logger logger = Logger
			.getLogger(LR1StateTransitionGraph.class);

	private final ConcurrentMap<LR1ItemSet, Integer> itemSet2Integer = new ConcurrentHashMap<LR1ItemSet, Integer>();
	private final List<LR1ItemSet> itemSetCollection = new ArrayList<LR1ItemSet>();
	private final ConcurrentMap<Integer, ConcurrentMap<Construction, Integer>> transitions = new ConcurrentHashMap<Integer, ConcurrentMap<Construction, Integer>>();

	private final Grammar grammar;
	private final Closure1 closure1;
	private final Goto1 goto1;

	public LR1StateTransitionGraph(Grammar grammar) {
		super();
		this.grammar = grammar;
		closure1 = new Closure1(grammar);
		goto1 = new Goto1(grammar);
		calculate();
	}

	/**
	 * From Dragon Book:
	 * 
	 * <pre>
	 *   void items(G') {
	 *   	C = {CLOSURE({[S' --> .S, $]})};
	 *   	repeat
	 *   		for ( jede Item-Menge I in C )
	 *  			for ( jedes Grammatiksymbol X )
	 *  				if ( GOTO( I, X) ist nicht leer und nicht in C )
	 *  					fuege GOTO(I, X) zu C hinzu;
	 *   	until es werden keine neuen Item-Mengen mehr in einer Runde zu C hinzugefuegt.
	 *   }
	 * </pre>
	 * 
	 * This method was extended to save also all transitions found and to handle
	 * ambiguous grammars.
	 * 
	 * @throws GrammarException
	 */
	protected void calculate() {
		LR1Item startItem = new LR1Item(grammar.getProductions().get(0), 0);
		startItem.addLookahead(FinishTerminal.getInstance());
		addState(closure1.calc(startItem));
		boolean changed;
		do {
			changed = false;
			int currentItemSetCount = itemSetCollection.size();
			logger.trace("State counter: " + currentItemSetCount);
			for (int stateId = 0; stateId < currentItemSetCount; stateId++) {
				LR1ItemSet itemSet = itemSetCollection.get(stateId);
				int initialState = itemSet2Integer.get(itemSet);
				for (Construction grammarSymbol : itemSet
						.getAllGrammarSymbols()) {
					LR1ItemSet gotoSet = goto1.calc(itemSet, grammarSymbol);
					if (gotoSet.getSize() > 0) {
						if (!itemSetCollection.contains(gotoSet)) {
							int newState = addState(gotoSet);
							addTransition(initialState, grammarSymbol, newState);
							changed = true;
						} else {
							int newState = itemSet2Integer.get(gotoSet);
							addTransition(initialState, grammarSymbol, newState);
						}
					}
				}
			}
		} while (changed);
	}

	private int addState(LR1ItemSet itemSet) {
		if (itemSet2Integer.containsKey(itemSet)) {
			return itemSet2Integer.get(itemSet);
		}
		int stateId = itemSetCollection.size();
		itemSetCollection.add(itemSet);
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

	public LR1ItemSet getItemSet(int stateId) {
		return itemSetCollection.get(stateId);
	}

	public int getStateNumber() {
		return itemSetCollection.size();
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
		for (int stateId = 0; stateId < itemSetCollection.size(); stateId++) {
			buffer.append("===========\n");
			buffer.append("State " + stateId + ":\n");
			buffer.append("===========\n");
			LR1ItemSet itemSet = itemSetCollection.get(stateId);
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
