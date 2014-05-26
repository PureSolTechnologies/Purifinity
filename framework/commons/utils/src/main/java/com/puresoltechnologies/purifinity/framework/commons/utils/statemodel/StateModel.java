package com.puresoltechnologies.purifinity.framework.commons.utils.statemodel;

import java.util.Set;

/**
 * This is an interface for a state model.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface StateModel<S extends State<S>> {

	/**
	 * This method returns the start state which is not valid, yet, but the
	 * starting point of the first transition to be made. The first transition
	 * may be on of multiple transitions.
	 * 
	 * The start state must have on valid transition!
	 * 
	 * @return
	 */
	public S getStartState();

	/**
	 * This method returns the possible end states of the model which show, that
	 * the model is finished and cannot be changed anymore. There are different
	 * possible end states due to the normal ends, aborts and errors.
	 * 
	 * These are the states which must not have a transition! All other states
	 * need to have.
	 * 
	 * @return A {@link Set} of state is returned which mark the end state of
	 *         the state model.
	 */
	public Set<S> getEndStates();

	/**
	 * This method returns the current {@link State} of the model.
	 * 
	 * @return The current {@link State} is returned.
	 */
	public State<S> getState();

	/**
	 * This method checks whether a transition can be performed with the current
	 * state.
	 */
	public boolean canPerformTransition(Transition<S> transition);

	/**
	 * This method performs the transition provided.
	 * 
	 * @param transition
	 * @throws IllegalStateException
	 *             is throw in case the transition is not valid for the current
	 *             state.
	 */
	public void performTransition(Transition<S> transition);

}
