package com.puresol.coding.client.common.ui.actions;

import org.eclipse.jface.action.Action;

import com.puresol.coding.client.common.branding.ClientImages;

/**
 * This action is used to refresh a part. The part using this action needs to
 * implement the Refreshable interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RefreshAction extends Action {

    private final Refreshable refreshable;

    public RefreshAction(Refreshable refreshable) {
	super("Refresh", ClientImages
		.getImageDescriptor(ClientImages.ARROW_REFRESH_16x16));
	this.refreshable = refreshable;
    }

    @Override
    public void run() {
	super.run();
	refreshable.refresh();
    }

}
