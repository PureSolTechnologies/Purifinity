package com.puresol.purifinity.client.common.ui.views;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.puresol.purifinity.client.common.ui.actions.OpenInExternalBrowserAction;
import com.puresol.purifinity.client.common.ui.actions.URIProvider;

public class BugsAndEnhancementsView extends ViewPart implements URIProvider {

	private static final String URL = "https://bugs.puresol-technologies.net";

	private Browser browser;

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		browser = new Browser(composite, SWT.NONE);
		browser.setUrl(URL);

		IToolBarManager toolBarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolBarManager.add(new OpenInExternalBrowserAction(this));
	}

	@Override
	public void setFocus() {
		browser.setFocus();
	}

	@Override
	public URI getURI() {
		try {
			return new URI(URL);
		} catch (URISyntaxException e) {
			return null;
		}
	}
}
