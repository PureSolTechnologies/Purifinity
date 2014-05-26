package com.puresoltechnologies.purifinity.framework.commons.utils.statemodel;

/**
 * This interface represents a single transition within the state model.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface Transition<S extends State<S>> {

	/**
	 * This method returns the localized name of the transition which is needed
	 * to be presented in a UI.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * This method returns the localized description of the transition which is
	 * needed to be presented in a UI.
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * This is the final state of the transition.
	 * 
	 * @return
	 */
	public S getFinalState();
}
