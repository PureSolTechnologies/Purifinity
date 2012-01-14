package com.puresol.coding.client.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class NewAnalysisStartPage extends WizardPage {

    protected NewAnalysisStartPage() {
	super("Source Directory");
	setTitle("General Settings");
	setMessage("Provide the general settings for the new analysis.");
    }

    @Override
    public void createControl(Composite parent) {
	setControl(new NewAnalysisDirectoryComposite(parent, SWT.None));
    }
}
