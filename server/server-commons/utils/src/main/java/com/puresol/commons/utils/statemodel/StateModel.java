package com.puresol.commons.utils.statemodel;

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
	 * This method returns the end state of the model which shows, that the
	 * model is finished and cannot be changed anymore.
	 * 
	 * This is the only state which must not have a valid transition!
	 * 
	 * @return
	 */
	public S getEndState();

	/**
	 * This method returns the current {@link State} of the model.
	 * 
	 * @return The current {@link State} is returned.
	 */
	public State<S> getState();

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
