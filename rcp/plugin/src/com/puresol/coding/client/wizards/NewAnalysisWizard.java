package com.puresol.coding.client.wizards;

import org.eclipse.jface.wizard.Wizard;

public class NewAnalysisWizard extends Wizard {

    public NewAnalysisWizard() {
	super();
	setWindowTitle("New Analysis");
	addPage(new NewAnalysisStartPage());
    }

    @Override
    public boolean performFinish() {
	// TODO Auto-generated method stub
	return false;
    }

}
