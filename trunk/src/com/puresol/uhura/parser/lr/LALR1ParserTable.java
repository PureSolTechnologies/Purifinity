package com.puresol.uhura.parser.lr;

import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.parser.parsetable.AbstractParserTable;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.LALR1StateTransitionGraph;
import com.puresol.uhura.parser.parsetable.LR1Item;
import com.puresol.uhura.parser.parsetable.LR1ItemSet;
import com.puresol.uhura.parser.parsetable.ParserAction;

public class LALR1ParserTable extends AbstractParserTable {

	private static final long serialVersionUID = 519035758380605579L;

	private static final Logger logger = Logger
			.getLogger(LALR1ParserTable.class);

	public LALR1ParserTable(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	protected void calculate() throws GrammarException {
		logger.debug("Calculate transition graph...");
		LALR1StateTransitionGraph transitionGraph = new LALR1StateTransitionGraph(
				getGrammar());
		if (logger.isTraceEnabled()) {
			logger.trace(transitionGraph.toString());
		}
		logger.debug("Adding shifts and gotos...");
		addShiftAndGotos(transitionGraph);
		logger.debug("Reduces and accept...");
		addReduceAndAccept(transitionGraph);
	}

	private void addShiftAndGotos(LALR1StateTransitionGraph transitionGraph)
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

	private void addReduceAndAccept(LALR1StateTransitionGraph transitionGraph)
			throws GrammarException {
		logger.trace("Add reduce and accept states to table...");
		Grammar grammar = getGrammar();
		for (int stateId = 0; stateId < transitionGraph.getStateNumber(); stateId++) {
			LR1ItemSet itemSet = transitionGraph.getItemSet(stateId);
			if (logger.isTraceEnabled()) {
				logger.debug("Process state " + stateId);
				logger.trace(itemSet);
			}
			for (LR1Item item : itemSet.getAllItems()) {
				if (item.hasNext()) {
					continue;
				}
				if (logger.isTraceEnabled()) {
					logger.trace(item);
				}
				if (item.getProduction()
						.equals(grammar.getProductions().get(0))) {
					logger.trace("Found state 'accept' action.");
					addActionTerminal(FinishTerminal.getInstance());
					addAction(stateId, FinishTerminal.getInstance(),
							new ParserAction(ActionType.ACCEPT, -1));
				} else {
					addAction(
							stateId,
							item.getLookahead(),
							new ParserAction(ActionType.REDUCE, grammar
									.getProductions().getId(
											item.getProduction())));
				}
			}
		}
	}
}