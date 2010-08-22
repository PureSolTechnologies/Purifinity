package com.puresol.uhura.parser.lr;

import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishConstruction;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.parser.parsetable.AbstractParserTable;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.Follow;
import com.puresol.uhura.parser.parsetable.Item;
import com.puresol.uhura.parser.parsetable.ItemSet;
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.StateTransitionGraph;

public class SLR1ParserTable extends AbstractParserTable {

	private static final Logger logger = Logger
			.getLogger(SLR1ParserTable.class);

	private Follow follow;
	private StateTransitionGraph transitionGraph;

	public SLR1ParserTable(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	protected void calculate() throws GrammarException {
		follow = new Follow(getGrammar());
		transitionGraph = new StateTransitionGraph(getGrammar());
		if (logger.isTraceEnabled()) {
			logger.trace(follow.toString());
			logger.trace(transitionGraph.toString());
		}
		addShiftAndGotos();
		addReduceAndAccept();
	}

	private void addShiftAndGotos() throws GrammarException {
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

	private void addReduceAndAccept() throws GrammarException {
		logger.trace("Add reduce and accept states to table...");
		Grammar grammar = getGrammar();
		for (int stateId = 0; stateId < transitionGraph.getStateNumber(); stateId++) {
			ItemSet itemSet = transitionGraph.getItemSet(stateId);
			if (logger.isTraceEnabled()) {
				logger.debug("Process state " + stateId);
				logger.trace(itemSet);
			}
			for (Item item : itemSet.getAllItems()) {
				if (item.hasNext()) {
					continue;
				}
				if (logger.isTraceEnabled()) {
					logger.trace(item);
				}
				if (item.getProduction().equals(
						grammar.getProductions().getProductions().get(0))) {
					logger.trace("Found state 'accept' action.");
					addActionTerminal(FinishConstruction.getInstance());
					addAction(stateId, FinishConstruction.getInstance(),
							new ParserAction(ActionType.ACCEPT, -1));
				} else if (!item.getProduction().getName()
						.equals(grammar.getProductions().get(0).getName())) {
					for (Construction construction : follow
							.get(new ProductionConstruction(item
									.getProduction().getName()))) {
						addAction(stateId, construction, new ParserAction(
								ActionType.REDUCE, grammar.getProductions()
										.getId(item.getProduction())));
					}
				}
			}
		}
	}

}
