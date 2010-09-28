package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;

public class StateTransitions implements Serializable {

	private static final long serialVersionUID = 3448400511750197925L;

	private final ConcurrentMap<Integer, ConcurrentMap<Construction, Integer>> transitions = new ConcurrentHashMap<Integer, ConcurrentMap<Construction, Integer>>();

	protected final void addTransition(int initialState,
			Construction construction, int targetState) throws GrammarException {
		ConcurrentMap<Construction, Integer> transitionMap = transitions
				.get(initialState);
		if (transitionMap == null) {
			transitionMap = new ConcurrentHashMap<Construction, Integer>();
			transitions.put(initialState, transitionMap);
		}
		Integer savedTargetState = transitions.get(initialState).get(
				construction);
		if (savedTargetState == null) {
			transitions.get(initialState).put(construction, targetState);
		} else {
			if (!savedTargetState.equals(targetState)) {
				throw new GrammarException("Ambiguous transitions found!");
			}
		}
	}

	public final ConcurrentMap<Construction, Integer> getTransitions(
			int initialState) {
		ConcurrentMap<Construction, Integer> result = transitions
				.get(initialState);
		if (result == null) {
			result = new ConcurrentHashMap<Construction, Integer>();
		}
		return result;
	}

	public final int getTransition(int initialState, Construction construction) {
		return transitions.get(initialState).get(construction);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((transitions == null) ? 0 : transitions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateTransitions other = (StateTransitions) obj;
		if (transitions == null) {
			if (other.transitions != null)
				return false;
		} else if (!transitions.equals(other.transitions))
			return false;
		return true;
	}
}
