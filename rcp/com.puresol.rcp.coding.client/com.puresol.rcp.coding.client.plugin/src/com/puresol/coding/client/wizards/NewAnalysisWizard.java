package com.puresol.coding.client.wizards;

import java.io.File;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.ProjectAnalyzerFactory;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.utils.PlatformUtils;
import com.puresol.coding.client.utils.PreferencesUtils;
import com.puresol.utils.FileSearchConfiguration;

public class NewAnalysisWizard extends Wizard {

    private final NewAnalysisGeneralSettingsPage generalSettingsPage = new NewAnalysisGeneralSettingsPage();

    public NewAnalysisWizard() {
	super();
	setWindowTitle("New Analysis");
	addPage(generalSettingsPage);
    }

    @Override
    public boolean performFinish() {
	IPreferenceStore preferenceStore = Activator.getDefault()
		.getPreferenceStore();
	FileSearchConfiguration searchConfiguration = PreferencesUtils
		.getFileSearchConfiguration(preferenceStore);
	String name = generalSettingsPage.getProjectName();
	String sourceDirectory = generalSettingsPage.getSourceDirectory();
	ProjectAnalyzer job = ProjectAnalyzerFactory.create(name, new File(
		sourceDirectory),
		new File(PlatformUtils.getWorkspaceDirectory(), name),
		searchConfiguration);
	job.schedule();
	return true;
    }
}
