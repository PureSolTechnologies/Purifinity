package com.puresol.uhura.parser.lr;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.parser.functions.Closure1;
import com.puresol.uhura.parser.functions.First;
import com.puresol.uhura.parser.functions.Goto1;
import com.puresol.uhura.parser.items.LR1Item;
import com.puresol.uhura.parser.items.LR1ItemSet;
import com.puresol.uhura.parser.parsetable.AbstractParserTable;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.ParserAction;

public class LR1ParserTable extends AbstractParserTable {

	private static final long serialVersionUID = 4897692245650199202L;

	private static final Logger logger = Logger.getLogger(LR1ParserTable.class);

	public LR1ParserTable(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	protected void calculate() throws GrammarException {
		logger.debug("Calculate item set collection...");
		First first = new First(getGrammar());
		Closure1 closure1 = new Closure1(getGrammar(), first);
		Goto1 goto1 = new Goto1(closure1);
		LR1ItemSetCollection itemSetCollection = new LR1ItemSetCollection(
				getGrammar(), closure1, goto1);
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
