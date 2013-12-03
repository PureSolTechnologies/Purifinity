package com.puresoltechnologies.purifinity.client.common.ui.actions;

import org.eclipse.ui.IWorkbenchPartSite;

/**
 * This interface needs to be implemented by all parts which need a part setting
 * dialog. This is used together with the part action {@link ShowSettingsAction}
 * .
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface PartSettingsCapability {

	String getTitle();

	IWorkbenchPartSite getSite();

	/**
	 * This method is called from the {@link ShowSettingsAction} when the
	 * settings dialog needs to be shown for the user.
	 * 
	 * The part's responsibility is to create and show the settings dialog.
	 */
	void showSettings();

	/**
	 * This method is called when the user hits the apply button on in the
	 * settings dialog. The part can take the reference to the settings dialog
	 * to read out the settings and to apply them accordingly.
	 */
	void applySettings();

	/**
	 * This method is called directly before the dialog is closing itself. This
	 * can be used to nullify the reference to the settings dialog and to
	 * perform some additional actions.
	 */
	void closeSettings();

}
