package com.puresol.purifinity.client.application;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IntroPart;

public class PurifinityIntroPart extends IntroPart implements SelectionListener {

	private static final String PURESOL_TECHNOLOGIES_URL = "http://puresol-technologies.com";

	private static final String BUGS_URL = "https://bugs.puresol-technologies.net";

	private Font titleFont = null;
	private Font sectionFont = null;
	private Color backgroundColor = null;
	private Link pstWebsiteLink;
	private Link bugsWebsiteLink;

	@Override
	public void dispose() {
		if (titleFont != null) {
			titleFont.dispose();
		}
		if (sectionFont != null) {
			sectionFont.dispose();
		}
		if (backgroundColor != null) {
			backgroundColor.dispose();
		}
		super.dispose();
	}

	@Override
	public void standbyStateChanged(boolean standby) {
		// intentionally left blank
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		backgroundColor = new Color(getIntroSite().getShell().getDisplay(),
				new RGB(255, 255, 255));
		parent.setBackground(backgroundColor);

		Label title = new Label(parent, SWT.NONE);
		Font font = title.getFont();
		FontData fontData = font.getFontData()[0];
		titleFont = new Font(getIntroSite().getShell().getDisplay(),
				fontData.getName(), fontData.getHeight() * 2,
				fontData.getStyle() | SWT.BOLD);
		sectionFont = new Font(getIntroSite().getShell().getDisplay(),
				fontData.getName(), (int) (fontData.getHeight() * 1.5),
				fontData.getStyle() | SWT.BOLD);
		title.setFont(titleFont);
		title.setText("Welcome to Purifinity");
		FormData fdTitle = new FormData();
		fdTitle.top = new FormAttachment(0, 10);
		fdTitle.left = new FormAttachment(0, 10);
		fdTitle.right = new FormAttachment(100, -10);
		title.setLayoutData(fdTitle);

		StyledText welcomeText = new StyledText(parent, SWT.MULTI | SWT.WRAP);
		welcomeText
				.setText("This is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text, because this is a long text... :-)");
		FormData fdWelcomeText = new FormData();
		fdWelcomeText.left = new FormAttachment(0, 10);
		fdWelcomeText.right = new FormAttachment(100, -10);
		fdWelcomeText.top = new FormAttachment(title, 0, 10);
		welcomeText.setLayoutData(fdWelcomeText);

		Label analyzersLabel = new Label(parent, SWT.NONE);
		analyzersLabel.setFont(sectionFont);
		analyzersLabel.setText("Installed Analyzers");
		FormData fdAnalyzersLabel = new FormData();
		fdAnalyzersLabel.left = new FormAttachment(0, 10);
		fdAnalyzersLabel.right = new FormAttachment(100, -10);
		fdAnalyzersLabel.top = new FormAttachment(welcomeText, 30);
		analyzersLabel.setLayoutData(fdAnalyzersLabel);

		Label evaluatorsLabel = new Label(parent, SWT.NONE);
		evaluatorsLabel.setFont(sectionFont);
		evaluatorsLabel.setText("Installed Evaluators");
		FormData fdEvaluatorsLabel = new FormData();
		fdEvaluatorsLabel.left = new FormAttachment(0, 10);
		fdEvaluatorsLabel.right = new FormAttachment(100, -10);
		fdEvaluatorsLabel.top = new FormAttachment(analyzersLabel, 30);
		evaluatorsLabel.setLayoutData(fdEvaluatorsLabel);

		Label linksLabel = new Label(parent, SWT.NONE);
		linksLabel.setFont(sectionFont);
		linksLabel.setText("Important Links");
		FormData fdLinksLabel = new FormData();
		fdLinksLabel.left = new FormAttachment(0, 10);
		fdLinksLabel.right = new FormAttachment(100, -10);
		fdLinksLabel.top = new FormAttachment(evaluatorsLabel, 30);
		linksLabel.setLayoutData(fdLinksLabel);

		pstWebsiteLink = new Link(parent, SWT.NONE);
		pstWebsiteLink.setText("<a>PureSol Technologies Website</a>");
		FormData fdPSTWebsiteLink = new FormData();
		fdPSTWebsiteLink.left = new FormAttachment(0, 10);
		fdPSTWebsiteLink.right = new FormAttachment(100, -10);
		fdPSTWebsiteLink.top = new FormAttachment(linksLabel, 0, 10);
		pstWebsiteLink.setLayoutData(fdPSTWebsiteLink);
		pstWebsiteLink.addSelectionListener(this);

		bugsWebsiteLink = new Link(parent, SWT.NONE);
		bugsWebsiteLink.setText("<a>Bug and Feature Request Tracker</a>");
		FormData fdBugsWebsiteLink = new FormData();
		fdBugsWebsiteLink.left = new FormAttachment(0, 10);
		fdBugsWebsiteLink.right = new FormAttachment(100, -10);
		fdBugsWebsiteLink.top = new FormAttachment(pstWebsiteLink, 0, 10);
		bugsWebsiteLink.setLayoutData(fdBugsWebsiteLink);
		bugsWebsiteLink.addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		pstWebsiteLink.setFocus();
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		if (event.getSource() == pstWebsiteLink) {
			openURL(PURESOL_TECHNOLOGIES_URL);
		} else if (event.getSource() == bugsWebsiteLink) {
			openURL(BUGS_URL);
		}
	}

	private void openURL(String URL) {
		try {
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser()
					.openURL(new URL(URL));
		} catch (PartInitException | MalformedURLException e) {
			throw new IllegalStateException(
					"Could not open URL '" + URL + "'.", e);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// intentionally, left blank
	}

}
