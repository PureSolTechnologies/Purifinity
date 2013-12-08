package com.puresoltechnologies.parser.impl.parser.lr;

import com.puresoltechnologies.parser.impl.grammar.GrammarException;
import com.puresoltechnologies.parser.impl.grammar.production.Construction;
import com.puresoltechnologies.parser.impl.parser.functions.Goto1;
import com.puresoltechnologies.parser.impl.parser.items.LR1ItemSet;
import com.puresoltechnologies.parser.impl.parser.parsetable.StateTransitions;

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
