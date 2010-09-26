package com.puresol.uhura.parser.parsetable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;

public class LR1ItemSetCollection extends GeneralStateTransitionGraph {

	private static final long serialVersionUID = -1330346621768260912L;

	private final static Logger logger = Logger
			.getLogger(LR1ItemSetCollection.class);

	private final ConcurrentMap<LR1ItemSet, Integer> itemSet2Integer = new ConcurrentHashMap<LR1ItemSet, Integer>();
	private final List<LR1ItemSet> itemSetCollection = new ArrayList<LR1ItemSet>();

	private final Grammar grammar;
	private final Closure1 closure1;
	private final Goto1 goto1;

	public LR1ItemSetCollection(Grammar grammar) throws GrammarException {
		super();
		this.grammar = grammar;
		closure1 = new Closure1(grammar, new First(grammar));
		goto1 = new Goto1(closure1);
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
	private void calculate() throws GrammarException {
		addState(closure1.calc(new LR1Item(grammar.getProductions().get(0), 0,
				FinishTerminal.getInstance())));
		int run = 0;
		int nextStartPosition = 0;
		int currentStartPosition = 0;
		do {
			run++;
			currentStartPosition = nextStartPosition;
			nextStartPosition = itemSetCollection.size();
			int currentItemSetCount = itemSetCollection.size();
			for (int stateId = currentStartPosition; stateId < currentItemSetCount; stateId++) {
				logger.trace("state: " + stateId + "/"
						+ itemSetCollection.size() + " run: " + run);
				LR1ItemSet itemSet = itemSetCollection.get(stateId);
				for (Construction grammarSymbol : itemSet
						.getAllGrammarSymbols()) {
					LR1ItemSet gotoSet = goto1.calc(itemSet, grammarSymbol);
					if (gotoSet.getSize() > 0) {
						int newState = addState(gotoSet);
						addTransition(stateId, grammarSymbol, newState);
					}
				}
			}
		} while (nextStartPosition < itemSetCollection.size());
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

	public LR1ItemSet getItemSet(int stateId) {
		return itemSetCollection.get(stateId);
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
