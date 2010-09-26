package com.puresol.uhura.parser.parsetable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;

public class LR0StateTransitionGraph extends GeneralStateTransitionGraph {

	private static final long serialVersionUID = -5320832167468349031L;

	private final ConcurrentMap<LR0ItemSet, Integer> itemSet2Integer = new ConcurrentHashMap<LR0ItemSet, Integer>();
	private final List<LR0ItemSet> itemSetCollection = new ArrayList<LR0ItemSet>();

	private final Grammar grammar;
	private final Closure0 closure0;
	private final Goto0 goto0;

	public LR0StateTransitionGraph(Grammar grammar) throws GrammarException {
		super();
		this.grammar = grammar;
		closure0 = new Closure0(grammar);
		goto0 = new Goto0(closure0);
		calculate();
	}

	/**
	 * From Dragon Book:
	 * 
	 * <pre>
	 *   void items(G') {
	 *   	C = {CLOSURE({[S0 --> .S]})};
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
	private void calculate() throws GrammarException {
		addState(closure0.calc(new LR0Item(grammar.getProductions().get(0), 0)));
		boolean changed;
		do {
			changed = false;
			int currentItemSetCount = itemSetCollection.size();
			for (int stateId = 0; stateId < currentItemSetCount; stateId++) {
				LR0ItemSet itemSet = itemSetCollection.get(stateId);
				int initialState = itemSet2Integer.get(itemSet);
				for (Construction grammarSymbol : itemSet
						.getAllGrammarSymbols()) {
					LR0ItemSet gotoSet = goto0.calc(itemSet, grammarSymbol);
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

	private int addState(LR0ItemSet itemSet) {
		Integer stateId = itemSet2Integer.get(itemSet);
		if (stateId == null) {
			stateId = itemSetCollection.size();
			itemSetCollection.add(itemSet);
			itemSet2Integer.put(itemSet, stateId);
		}
		return stateId;
	}

	public LR0ItemSet getItemSet(int stateId) {
		return (LR0ItemSet) itemSetCollection.toArray()[stateId];
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
			LR0ItemSet itemSet = (LR0ItemSet) itemSetCollection.toArray()[stateId];
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
}
