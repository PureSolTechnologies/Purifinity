package com.puresol.uhura.parser.parsetable;

import com.puresol.uhura.grammar.Grammar;

public class LALR1StateTransitionGraph extends LR1StateTransitionGraph {

	private static final long serialVersionUID = 6813332818983681476L;

	public LALR1StateTransitionGraph(Grammar grammar) {
		super(grammar);
	}

	protected void calculate() {
		super.calculate();
		reduceToLALR1();
	}

	private void reduceToLALR1() {
		/*
		 * search all present states....
		 */
		int maxState = getStateNumber() - 1;
		for (int state = 0; state <= maxState; state++) {
			if (getItemSet(state) != null) {
				reduceState(state, maxState);
			}
		}
	}

	private void reduceState(int currentState, int maxState) {
		LR1ItemSet currentItem = getItemSet(currentState);
		for (int state = currentState; currentState <= maxState; state++) {
			LR1ItemSet itemSet = getItemSet(currentState);
			if (itemSet == null) {
				continue;
			}
			if (currentItem.equalsCore(itemSet)) {
				join(currentState, state);
			}
		}
	}

	private void join(int currentState, int state) {
		// LR1ItemSet current = getItemSet(currentState);
		// LR1ItemSet other = getItemSet(currentState);
		// TODO
	}
}
