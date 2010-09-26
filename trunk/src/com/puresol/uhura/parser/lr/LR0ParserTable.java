package com.puresol.uhura.parser.lr;

import java.util.concurrent.ConcurrentMap;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.parser.parsetable.AbstractParserTable;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.LR0Item;
import com.puresol.uhura.parser.parsetable.LR0ItemSet;
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.LR0ItemSetCollection;

public class LR0ParserTable extends AbstractParserTable {

	private static final long serialVersionUID = 4063891308079169101L;

	public LR0ParserTable(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected void calculate() throws GrammarException {
		LR0ItemSetCollection transitionGraph = new LR0ItemSetCollection(
				getGrammar());
		addShiftAndGotos(transitionGraph);
		addReduceAndAccept(transitionGraph);
	}

	private void addShiftAndGotos(LR0ItemSetCollection transitionGraph)
			throws GrammarException {
		for (int stateId = 0; stateId < transitionGraph.getStateNumber(); stateId++) {
			ConcurrentMap<Construction, Integer> transitions = transitionGraph
					.getTransitions(stateId);
			for (Construction construction : transitions.keySet()) {
				if (construction.isTerminal()) {
					addAction(stateId, construction, new ParserAction(
							ActionType.SHIFT, transitions.get(construction)));
					if (!getActionTerminals().contains(construction)) {
						addActionTerminal(construction);
					}
				} else {
					addAction(stateId, construction, new ParserAction(
							ActionType.GOTO, transitions.get(construction)));
					if (!getGotoNonTerminals().contains(construction)) {
						addGotoNonTerminal(construction);
					}
				}
			}
		}
	}

	private void addReduceAndAccept(LR0ItemSetCollection transitionGraph)
			throws GrammarException {
		Grammar grammar = getGrammar();
		for (int stateId = 0; stateId < transitionGraph.getStateNumber(); stateId++) {
			LR0ItemSet itemSet = transitionGraph.getItemSet(stateId);
			for (LR0Item item : itemSet.getAllItems()) {
				if (item.getNext() != null) {
					continue;
				}
				if (item.getProduction()
						.equals(grammar.getProductions().get(0))) {
					addActionTerminal(FinishTerminal.getInstance());
					addAction(stateId, FinishTerminal.getInstance(),
							new ParserAction(ActionType.ACCEPT, -1));
				} else {
					for (Construction construction : getActionTerminals()) {
						addAction(stateId, construction, new ParserAction(
								ActionType.REDUCE, grammar.getProductions()
										.getId(item.getProduction())));
					}
				}
			}
		}
	}

}
