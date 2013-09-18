package com.puresol.purifinity.client.application.commands;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class OpenPureSolTechnologiesWebsiteHandler extends AbstractHandler {

	private static final String URL = "http://puresol-technologies.com";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			Desktop.getDesktop().browse(new URI(URL));
		} catch (IOException | URISyntaxException e) {
			throw new IllegalStateException(
					"Could not open URL '" + URL + "'.", e);
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		return Desktop.isDesktopSupported();
	}

	@Override
	public boolean isHandled() {
		return Desktop.isDesktopSupported();
	}
}
