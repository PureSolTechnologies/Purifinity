package com.puresoltechnologies.purifinity.client.application.commands;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;

public class OpenBugAndEnhancementTrackerWebsiteHandler extends AbstractHandler {

	private static final String URL = "https://bugs.puresol-technologies.com";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			IWebBrowser externalBrowser = PlatformUI.getWorkbench()
					.getBrowserSupport().getExternalBrowser();
			externalBrowser.openURL(new URL(URL));
		} catch (PartInitException | MalformedURLException e) {
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
