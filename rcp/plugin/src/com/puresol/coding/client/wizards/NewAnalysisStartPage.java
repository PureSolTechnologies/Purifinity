package com.puresol.coding.client.wizards;

import javax.i18n4java.Translator;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class NewAnalysisStartPage extends WizardPage {

    private static final Translator translator = Translator
	    .getTranslator(NewAnalysisStartPage.class);

    protected NewAnalysisStartPage() {
	super("Source Directory");
	setTitle(translator.i18n("General Settings"));
	setMessage(translator
		.i18n("Provide the general settings for the new analysis."));
    }

    @Override
    public void createControl(Composite parent) {
	setControl(new NewAnalysisDirectoryComposite(parent, SWT.None));
    }
}
