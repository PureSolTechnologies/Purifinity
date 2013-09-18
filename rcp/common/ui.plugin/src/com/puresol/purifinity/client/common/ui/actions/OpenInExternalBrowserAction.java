package com.puresol.purifinity.client.common.ui.actions;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.eclipse.jface.action.Action;

import com.puresol.purifinity.client.common.branding.ClientImages;

/**
 * This action is used to start an external browser with an URI defined by an
 * {@link URIProvider}, which might be a view.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class OpenInExternalBrowserAction extends Action {

	private final URIProvider uriProvider;

	public OpenInExternalBrowserAction(URIProvider uriProvider) {
		super("Open in External Browser", ClientImages
				.getImageDescriptor(ClientImages.APPLICATION_GO_16x16));
		this.uriProvider = uriProvider;
		setToolTipText("Exports the content of this view into a specified format to a specified location.");
	}

	@Override
	public void run() {
		super.run();
		URI uri = uriProvider.getURI();
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException e) {
			throw new IllegalStateException(
					"Could not open URI '" + uri + "'.", e);
		}
	}
}
