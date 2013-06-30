package com.puresol.purifinity.client.common.ui.actions;

/**
 * This interface is implemented by parts to use the {@link InformationAction}
 * action to show additional information about the part itself or a selection
 * within the part.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface InformationProvider {

	/**
	 * This method is called by {@link InformationAction} when the user
	 * triggered the button to show additional information. The implementing
	 * part needs to take care of presenting the information.
	 */
	public void showInformation();

}
