package com.puresol.purifinity.client.common.ui.actions;

import org.eclipse.jface.action.Action;

import com.puresol.purifinity.client.common.branding.ClientImages;
import com.puresol.purifinity.client.common.branding.Exportable;

/**
 * This action is used to refresh a part. The part using this action needs to
 * implement the Refreshable interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExportAction extends Action {

    private final Exportable exportable;

    public ExportAction(Exportable exportable) {
	super("Export", ClientImages
		.getImageDescriptor(ClientImages.DOCUMENT_EXPORT_16x16));
	this.exportable = exportable;
	setToolTipText("Exports the content of this view into a specified format to a specified location.");
    }

    @Override
    public void run() {
	super.run();
	exportable.export();
    }

}
