package com.puresol.uhura.parser.lr;

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
import com.puresol.uhura.parser.parsetable.AbstractParserTable;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.Closure1;
import com.puresol.uhura.parser.parsetable.First;
import com.puresol.uhura.parser.parsetable.Goto1;
import com.puresol.uhura.parser.parsetable.LALR1StateTransitionGraph;
import com.puresol.uhura.parser.parsetable.LR1Item;
import com.puresol.uhura.parser.parsetable.LR1ItemSet;
import com.puresol.uhura.parser.parsetable.ParserAction;

public class LALR1ParserTable extends AbstractParserTable {

	private static final long serialVersionUID = 519035758380605579L;

	private static final Logger logger = Logger
			.getLogger(LALR1ParserTable.class);

	private final ConcurrentMap<LR1ItemSet, Integer> itemSet2Integer = new ConcurrentHashMap<LR1ItemSet, Integer>();
	private final List<LR1ItemSet> itemSetCollection = new ArrayList<LR1ItemSet>();
	private final ConcurrentMap<Integer, ConcurrentMap<Construction, Integer>> transitions = new ConcurrentHashMap<Integer, ConcurrentMap<Construction, Integer>>();

	private final LALR1StateTransitionGraph transitionGraph;
	private final First first;
	private final Closure1 closure1;
	private final Goto1 goto1;

	public LALR1ParserTable(Grammar grammar) throws GrammarException {
		super(grammar);
		first = new First(grammar);
		closure1 = new Closure1(grammar, first);
		goto1 = new Goto1(closure1);
		transitionGraph = new LALR1StateTransitionGraph(getGrammar());
		calculate();
	}

	protected void calculate() throws GrammarException {
	}

	public LALR1StateTransitionGraph getTransitionGraph() {
		return this.transitionGraph;
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
			int finalState) throws GrammarException {
		if (!transitions.containsKey(initialState)) {
			transitions.put(initialState,
					new ConcurrentHashMap<Construction, Integer>());
		}
		if (transitions.containsKey(construction)) {
			if (!transitions.get(initialState).get(construction)
					.equals(finalState)) {
				throw new GrammarException("Ambiguous transitions found!");
			}
		} else {
			transitions.get(initialState).put(construction, finalState);
		}
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
}