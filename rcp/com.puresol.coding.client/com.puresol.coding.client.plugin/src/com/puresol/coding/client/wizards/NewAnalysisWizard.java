package com.puresol.coding.client.wizards;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.Wizard;

import com.puresol.coding.client.Activator;

public class NewAnalysisWizard extends Wizard {

    private final ILog logger = Activator.getDefault().getLog();

    public NewAnalysisWizard() {
	super();
	setWindowTitle("New Analysis");
	addPage(new NewAnalysisGeneralSettingsPage());
    }

    @Override
    public boolean performFinish() {
	logger.log(new Status(Status.INFO, getClass().getName(),
		"Creating new analysis..."));
	// TODO Auto-generated method stub
	return false;
    }
}
