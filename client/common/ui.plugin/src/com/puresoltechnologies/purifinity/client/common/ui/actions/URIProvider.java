package com.puresoltechnologies.purifinity.client.common.ui.actions;

import java.net.URI;

/**
 * This interface is implemented for OpenInExternalBrowserAction and other
 * actions which open external application for the content shown in a view.
 * 
 * The view needs tom provide the content to be shown for the external
 * application as URI.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface URIProvider {

	/**
	 * This method returns the URI which is to be handles by the action.
	 * 
	 * @return An {@link URI} object is returned.
	 */
	public URI getURI();
}
