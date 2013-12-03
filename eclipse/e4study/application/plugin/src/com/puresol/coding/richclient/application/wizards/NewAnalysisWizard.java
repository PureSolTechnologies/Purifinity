package com.puresol.coding.richclient.application.wizards;

import java.io.File;

import org.eclipse.jface.wizard.Wizard;

import com.puresoltechnologies.coding.analysis.api.DirectoryRepositoryLocation;
import com.puresoltechnologies.coding.richclient.application.jobs.NewAnalysisJob;

public class NewAnalysisWizard extends Wizard {

	private final NewAnalysisGeneralSettingsPage generalSettingsPage;

	public NewAnalysisWizard() {
		super();
		setWindowTitle("New Analysis");
		generalSettingsPage = new NewAnalysisGeneralSettingsPage();
		addPage(generalSettingsPage);
	}

	@Override
	public boolean performFinish() {
		String name = generalSettingsPage.getProjectName();
		String description = generalSettingsPage.getProjectDescription();
		String sourceDirectory = generalSettingsPage.getSourceDirectory();
		NewAnalysisJob job = new NewAnalysisJob(
				name,
				description,
				new DirectoryRepositoryLocation(name, new File(sourceDirectory)));
		job.schedule();
		return true;
	}
}
