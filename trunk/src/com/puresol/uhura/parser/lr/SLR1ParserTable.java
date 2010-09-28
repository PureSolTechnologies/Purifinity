package com.puresol.uhura.parser.lr;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.parser.parsetable.AbstractParserTable;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.Closure0;
import com.puresol.uhura.parser.parsetable.First;
import com.puresol.uhura.parser.parsetable.Follow;
import com.puresol.uhura.parser.parsetable.Goto0;
import com.puresol.uhura.parser.parsetable.LR0Item;
import com.puresol.uhura.parser.parsetable.LR0ItemSet;
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.LR0ItemSetCollection;

public class SLR1ParserTable extends AbstractParserTable {

	private static final long serialVersionUID = 519035758380605579L;

	private static final Logger logger = Logger
			.getLogger(SLR1ParserTable.class);

	public SLR1ParserTable(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	protected void calculate() throws GrammarException {
		logger.debug("Calculate item set collection...");
		First first = new First(getGrammar());
		Follow follow = new Follow(getGrammar(), first);
		Closure0 closure0 = new Closure0(getGrammar());
		Goto0 goto0 = new Goto0(closure0);
		LR0ItemSetCollection itemSetCollection = new LR0ItemSetCollection(
				getGrammar(), closure0, goto0);
		logger.debug("Create parser table...");
		for (int state = 0; state < itemSetCollection.getStateNumber(); state++) {
			if (logger.isTraceEnabled()) {
				logger.trace("state: " + state + "/"
						+ itemSetCollection.getStateNumber());
			}
			LR0ItemSet lr0ItemSet = itemSetCollection.getItemSet(state);
			for (LR0Item lr0Item : lr0ItemSet.getAllItems()) {
				if (lr0Item.hasNext()) {
					Construction next = lr0Item.getNext();
					LR0ItemSet targetSet = goto0.calc(lr0ItemSet, next);
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
				} else if (lr0Item.getProduction().equals(
						getGrammar().getProductions().get(0))) {
					// has not next and is not start production
					addAction(state, FinishTerminal.getInstance(),
							new ParserAction(ActionType.ACCEPT, -1));
					addActionTerminal(FinishTerminal.getInstance());
				} else { // has not next and is not start production
					for (Construction lookahead : follow.get(new NonTerminal(
							lr0Item.getProduction().getName()))) {
						addAction(state, lookahead, new ParserAction(
								ActionType.REDUCE, lr0Item.getProduction()
										.getId()));
					}
				}
			}
		}
		logger.debug("done.");
	}
}
