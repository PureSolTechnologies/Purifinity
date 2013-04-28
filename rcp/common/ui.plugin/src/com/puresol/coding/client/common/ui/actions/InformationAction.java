package com.puresol.coding.client.common.ui.actions;

import org.eclipse.jface.action.Action;

import com.puresol.coding.client.common.branding.ClientImages;

/**
 * This action is used to show additional information in a part for a selection
 * or the part. The part using this action needs to implement the
 * InformationProvider interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InformationAction extends Action {

    private final InformationProvider infoProvider;

    public InformationAction(InformationProvider infoProvider) {
	super("Refresh", ClientImages
		.getImageDescriptor(ClientImages.INFORMATION_16x16));
	setToolTipText("Shows additional, detailed information about the view and selection.");
	this.infoProvider = infoProvider;
    }

    @Override
    public void run() {
	super.run();
	infoProvider.showInformation();
    }

}
