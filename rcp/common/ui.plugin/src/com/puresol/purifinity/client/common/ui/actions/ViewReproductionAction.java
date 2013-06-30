package com.puresol.purifinity.client.common.ui.actions;

import java.util.UUID;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;

import com.puresol.purifinity.client.common.branding.ClientImages;

/**
 * This action is used to refresh a part. The part using this action needs to
 * implement the Refreshable interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ViewReproductionAction extends Action {

    private final Reproducable reproducable;

    public ViewReproductionAction(Reproducable reproducable) {
	super("Reproduce", ClientImages
		.getImageDescriptor(ClientImages.TAB_ADD_16x16));
	setToolTipText("Opens another view of this kind. The second view can be customized individually.");
	this.reproducable = reproducable;
    }

    @Override
    public void run() {
	try {
	    super.run();
	    String id = reproducable.getClass().getName();
	    IWorkbenchPartSite site = reproducable.getSite();
	    IWorkbenchPage page = site.getPage();
	    IViewPart showView = page.showView(id,
		    UUID.randomUUID().toString(), IWorkbenchPage.VIEW_CREATE);
	    showView.init(showView.getViewSite());
	} catch (PartInitException e) {
	    e.printStackTrace();
	}
    }
}
