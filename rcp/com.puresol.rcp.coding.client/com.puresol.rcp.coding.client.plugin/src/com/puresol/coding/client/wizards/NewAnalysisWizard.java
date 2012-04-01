package com.puresol.coding.client.wizards;

import java.io.File;

import org.eclipse.jface.wizard.Wizard;

public class NewAnalysisWizard extends Wizard {

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
	NewAnalysisJob job = new NewAnalysisJob(name, new File(sourceDirectory));
	job.schedule();
	return true;
    }
}
