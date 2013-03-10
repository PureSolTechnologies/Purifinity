package com.puresol.coding.client.application.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.wb.swt.ResourceManager;

/**
 * This action is used to refresh a part. The part using this action needs to
 * implement the Refreshable interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RefreshAction extends Action {

    private static final String RESOURCE_PLUGIN = "com.puresol.coding.client.application.plugin";
    private static final String ICON_RESOURCE = "icons/16x16/arrow_refresh.png";

    private final Refreshable refreshable;

    public RefreshAction(Refreshable refreshable) {
	super("Refresh", ResourceManager.getPluginImageDescriptor(
		RESOURCE_PLUGIN, ICON_RESOURCE));
	this.refreshable = refreshable;
    }

    @Override
    public void run() {
	super.run();
	refreshable.refresh();
    }

}
