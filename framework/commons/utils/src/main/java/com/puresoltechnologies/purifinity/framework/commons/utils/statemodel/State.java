package com.puresoltechnologies.purifinity.framework.commons.utils.statemodel;

import java.util.Set;

/**
 * This is a single state of the system within the state model.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface State<S extends State<S>> {

	/**
	 * This method returns the localized name of the state which is needed to be
	 * presented in a UI.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * This method returns the localized description of the state which is
	 * needed to be presented in a UI.
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * This method returns the valid, applicable transitions of the state.
	 * 
	 * The list of transition must not be null! An empty is list is to be
	 * returned if no transition can be found!
	 * 
	 * @return
	 */
	public Set<Transition<S>> getTransitions();

}
