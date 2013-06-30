package com.puresol.purifinity.client.common.ui.actions;

import org.eclipse.jface.action.Action;

import com.puresol.purifinity.client.common.branding.ClientImages;

/**
 * This action is used to show and hide settings of a part. The part using this
 * action needs to implement the ShowAndHideSettings interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ShowSettingsAction extends Action {

    private final PartSettingsCapability component;

    public ShowSettingsAction(PartSettingsCapability component) {
	super("Settings...", ClientImages
		.getImageDescriptor(ClientImages.TAB_EDIT_16x16));
	setToolTipText("Shows a dialog which provides the ability to customize this view.");
	this.component = component;
    }

    @Override
    public void run() {
	super.run();
	component.showSettings();
    }
}
