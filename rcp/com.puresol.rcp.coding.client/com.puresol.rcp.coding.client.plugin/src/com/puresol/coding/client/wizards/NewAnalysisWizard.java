package com.puresol.coding.client.wizards;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.Wizard;

import com.puresol.coding.client.Activator;

public class NewAnalysisWizard extends Wizard {

    private final ILog logger = Activator.getDefault().getLog();
    private final NewAnalysisGeneralSettingsPage generalSettingsPage = new NewAnalysisGeneralSettingsPage();

    public NewAnalysisWizard() {
	super();
	setWindowTitle("New Analysis");
	addPage(generalSettingsPage);
    }

    @Override
    public boolean performFinish() {
	String name = generalSettingsPage.getProjectName();
	String sourceDirectory = generalSettingsPage.getSourceDirectory();
	logger.log(new Status(Status.INFO, getClass().getName(),
		"Creating new analysis " + name + " for directory "
			+ sourceDirectory + "..."));
	return false;
    }
}
