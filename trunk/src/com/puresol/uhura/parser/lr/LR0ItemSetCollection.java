package com.puresol.uhura.parser.lr;

import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.parser.functions.Closure0;
import com.puresol.uhura.parser.functions.Goto0;
import com.puresol.uhura.parser.items.LR0Item;
import com.puresol.uhura.parser.items.LR0ItemSet;

public class LR0ItemSetCollection {

	private static final long serialVersionUID = -5320832167468349031L;

	private final List<LR0ItemSet> itemSetCollection = new ArrayList<LR0ItemSet>();

	private final Grammar grammar;
	private final Closure0 closure0;
	private final Goto0 goto0;

	public LR0ItemSetCollection(Grammar grammar, Closure0 closure0, Goto0 goto0)
			throws GrammarException {
		super();
		this.grammar = grammar;
		this.closure0 = closure0;
		this.goto0 = goto0;
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
		int currentSize;
		do {
			currentSize = itemSetCollection.size();
			int currentItemSetCount = itemSetCollection.size();
			for (int stateId = 0; stateId < currentItemSetCount; stateId++) {
				LR0ItemSet itemSet = itemSetCollection.get(stateId);
				for (Construction grammarSymbol : itemSet
						.getAllGrammarSymbols()) {
					LR0ItemSet gotoSet = goto0.calc(itemSet, grammarSymbol);
					if (gotoSet.getSize() > 0) {
						addState(gotoSet);
					}
				}
			}
		} while (currentSize < itemSetCollection.size());
	}

	private void addState(LR0ItemSet itemSet) {
		if (!itemSetCollection.contains(itemSet)) {
			itemSetCollection.add(itemSet);
		}
	}

	public LR0ItemSet getItemSet(int stateId) {
		return (LR0ItemSet) itemSetCollection.toArray()[stateId];
	}

	/**
	 * This method looks for the state id for a given item set.
	 * 
	 * @param targetSet
	 * @return
	 * @throws GrammarException
	 */
	public int getStateId(LR0ItemSet targetSet) throws GrammarException {
		for (int stateId = 0; stateId < itemSetCollection.size(); stateId++) {
			LR0ItemSet lr0ItemSet = itemSetCollection.get(stateId);
			if (lr0ItemSet.equals(targetSet)) {
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
			LR0ItemSet itemSet = (LR0ItemSet) itemSetCollection.toArray()[stateId];
			buffer.append(itemSet.toString());
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
