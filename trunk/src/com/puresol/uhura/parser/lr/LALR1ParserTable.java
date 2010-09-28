package com.puresol.uhura.parser.lr;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.parser.parsetable.AbstractParserTable;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.Closure0;
import com.puresol.uhura.parser.parsetable.Closure1;
import com.puresol.uhura.parser.parsetable.First;
import com.puresol.uhura.parser.parsetable.Goto0;
import com.puresol.uhura.parser.parsetable.Goto1;
import com.puresol.uhura.parser.parsetable.LALR1ItemSetCollection;
import com.puresol.uhura.parser.parsetable.LR0ItemSetCollection;
import com.puresol.uhura.parser.parsetable.LR0StateTransitions;
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
		logger.debug("Calculate item set collection...");
		First first = new First(getGrammar());
		Closure0 closure0 = new Closure0(getGrammar());
		Goto0 goto0 = new Goto0(closure0);
		Closure1 closure1 = new Closure1(getGrammar(), first);
		Goto1 goto1 = new Goto1(closure1);
		LR0ItemSetCollection lr0ItemSetCollection = new LR0ItemSetCollection(
				getGrammar(), closure0, goto0);
		LR0StateTransitions lr0Transitions = new LR0StateTransitions(
				lr0ItemSetCollection, goto0);
		LALR1ItemSetCollection itemSetCollection = new LALR1ItemSetCollection(
				getGrammar(), lr0ItemSetCollection, lr0Transitions, closure1);
		logger.debug("Create parser table...");
		for (int state = 0; state < itemSetCollection.getStateNumber(); state++) {
			if (logger.isTraceEnabled()) {
				logger.trace("state: " + state + "/"
						+ itemSetCollection.getStateNumber());
			}
			LR1ItemSet lr1ItemSet = itemSetCollection.getItemSet(state);
			for (LR1Item lr1Item : lr1ItemSet.getAllItems()) {
				if (lr1Item.hasNext()) {
					Construction next = lr1Item.getNext();
					LR1ItemSet targetSet = goto1.calc(lr1ItemSet, next);
					int targetState = itemSetCollection.getStateId(targetSet);
					if (next.isTerminal()) {
						addAction(state, next, new ParserAction(
								ActionType.SHIFT, targetState));
						addActionTerminal(next);
					} else {
						addAction(state, next, new ParserAction(
								ActionType.GOTO, targetState));
						addGotoNonTerminal(next);
					}
				} else if (lr1Item.getProduction().equals(
						getGrammar().getProductions().get(0))) {
					// has not next and is not start production
					addAction(state, FinishTerminal.getInstance(),
							new ParserAction(ActionType.ACCEPT, -1));
					addActionTerminal(FinishTerminal.getInstance());
				} else { // has not next and is not start production
					addAction(state, lr1Item.getLookahead(), new ParserAction(
							ActionType.REDUCE, lr1Item.getProduction().getId()));
				}
			}
		}
		logger.debug("done.");
	}
}