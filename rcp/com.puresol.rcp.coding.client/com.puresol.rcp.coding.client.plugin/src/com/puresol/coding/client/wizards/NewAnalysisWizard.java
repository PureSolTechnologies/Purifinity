package com.puresol.coding.client.wizards;

import java.io.File;

import org.eclipse.jface.wizard.Wizard;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.ProjectAnalyzerFactory;
import com.puresol.coding.client.utils.PlatformUtils;

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
	ProjectAnalyzer job = ProjectAnalyzerFactory.create(new File(
		sourceDirectory),
		new File(PlatformUtils.getWorkspaceDirectory(), name));
	job.schedule();
	return true;
    }
}
