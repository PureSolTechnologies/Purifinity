package com.puresoltechnologies.purifinity.framework.commons.utils.statemodel;

/**
 * This is the first abstract implementation of a state model.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractStateModel<S extends State<S>> implements
		StateModel<S> {

	private S currentState;

	/**
	 * This is the default constructor. It starts the model at the start state
	 * as returned in {@link #getStartState()}.
	 */
	public AbstractStateModel() {
		super();
		currentState = getStartState();
	}

	/**
	 * This constructor starts at a given initial state. This can be useful in
	 * cases were a model was persisted to restart it.
	 * 
	 * @param initialState
	 */
	public AbstractStateModel(S initialState) {
		super();
		currentState = initialState;
	}

	@Override
	public final S getState() {
		return currentState;
	}

	@Override
	public boolean canPerformTransition(Transition<S> transition) {
		return currentState.getTransitions().contains(transition);
	}

	@Override
	public final void performTransition(Transition<S> transition) {
		if (!canPerformTransition(transition)) {
			throw new IllegalStateException("Transition '"
					+ transition.getName() + "' is not allowed in state '"
					+ currentState.getName() + "'");
		}
		currentState = transition.getFinalState();
	}

}
