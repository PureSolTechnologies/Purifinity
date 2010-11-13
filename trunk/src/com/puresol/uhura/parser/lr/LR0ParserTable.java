package com.puresol.uhura.parser.lr;

import java.util.Map;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.parser.functions.Closure0;
import com.puresol.uhura.parser.functions.Goto0;
import com.puresol.uhura.parser.items.LR0Item;
import com.puresol.uhura.parser.items.LR0ItemSet;
import com.puresol.uhura.parser.parsetable.AbstractParserTable;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.ParserAction;

public class LR0ParserTable extends AbstractParserTable {

	private static final long serialVersionUID = 4063891308079169101L;

	public LR0ParserTable(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected void calculate() throws GrammarException {
		Closure0 closure0 = new Closure0(getGrammar());
		Goto0 goto0 = new Goto0(closure0);
		LR0ItemSetCollection transitionGraph = new LR0ItemSetCollection(
				getGrammar(), closure0, goto0);
		LR0StateTransitions stateTransitions = new LR0StateTransitions(
				transitionGraph, goto0);
		addShiftAndGotos(transitionGraph, stateTransitions);
		addReduceAndAccept(transitionGraph);
	}

	private void addShiftAndGotos(LR0ItemSetCollection itemSetCollection,
			LR0StateTransitions stateTransitions) throws GrammarException {
		for (int stateId = 0; stateId < itemSetCollection.getStateNumber(); stateId++) {
			Map<Construction, Integer> transitions = stateTransitions
					.getTransitions(stateId);
			for (Construction construction : transitions.keySet()) {
				if (construction.isTerminal()) {
					addAction(stateId, construction, new ParserAction(
							ActionType.SHIFT, transitions.get(construction)));
					if (!getActionTerminals().contains(construction)) {
						addActionTerminal((Terminal) construction);
					}
				} else {
					addAction(stateId, construction, new ParserAction(
							ActionType.GOTO, transitions.get(construction)));
					if (!getGotoNonTerminals().contains(construction)) {
						addGotoNonTerminal((NonTerminal) construction);
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
