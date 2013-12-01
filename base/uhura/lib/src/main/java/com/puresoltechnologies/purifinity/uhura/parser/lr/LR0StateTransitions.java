package com.puresoltechnologies.purifinity.uhura.parser.lr;

import com.puresoltechnologies.purifinity.uhura.grammar.GrammarException;
import com.puresoltechnologies.purifinity.uhura.grammar.production.Construction;
import com.puresoltechnologies.purifinity.uhura.parser.functions.Goto0;
import com.puresoltechnologies.purifinity.uhura.parser.items.LR0ItemSet;
import com.puresoltechnologies.purifinity.uhura.parser.parsetable.StateTransitions;

public class LR0StateTransitions extends StateTransitions {

	private static final long serialVersionUID = -5970793225278358360L;

	private final LR0ItemSetCollection itemSetCollection;
	private final Goto0 goto0;

	public LR0StateTransitions(LR0ItemSetCollection itemSetCollection,
			Goto0 goto0) throws GrammarException {
		super();
		this.itemSetCollection = itemSetCollection;
		this.goto0 = goto0;
		calculate();
	}

	private void calculate() throws GrammarException {
		for (int stateId = 0; stateId < itemSetCollection.getStateNumber(); stateId++) {
			LR0ItemSet lr0ItemSet = itemSetCollection.getItemSet(stateId);
			for (Construction next : lr0ItemSet.getNextConstructions()) {
				LR0ItemSet gotoSet = goto0.calc(lr0ItemSet, next);
				int targetState = itemSetCollection.getStateId(gotoSet);
				addTransition(stateId, next, targetState);
			}
		}
	}

}
