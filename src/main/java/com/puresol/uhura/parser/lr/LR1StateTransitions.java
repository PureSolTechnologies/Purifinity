package com.puresol.uhura.parser.lr;

import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.parser.functions.Goto1;
import com.puresol.uhura.parser.items.LR1ItemSet;
import com.puresol.uhura.parser.parsetable.StateTransitions;

public class LR1StateTransitions extends StateTransitions {

	private static final long serialVersionUID = -5970793225278358360L;

	private final LR1ItemSetCollection itemSetCollection;
	private final Goto1 goto1;

	public LR1StateTransitions(LR1ItemSetCollection itemSetCollection,
			Goto1 goto1) throws GrammarException {
		super();
		this.itemSetCollection = itemSetCollection;
		this.goto1 = goto1;
		calculate();
	}

	private void calculate() throws GrammarException {
		for (int stateId = 0; stateId < itemSetCollection.getStateNumber(); stateId++) {
			LR1ItemSet lr1ItemSet = itemSetCollection.getItemSet(stateId);
			for (Construction next : lr1ItemSet.getNextConstructions()) {
				LR1ItemSet gotoSet = goto1.calc(lr1ItemSet, next);
				int targetState = itemSetCollection.getStateId(gotoSet);
				addTransition(stateId, next, targetState);
			}
		}
	}

}
