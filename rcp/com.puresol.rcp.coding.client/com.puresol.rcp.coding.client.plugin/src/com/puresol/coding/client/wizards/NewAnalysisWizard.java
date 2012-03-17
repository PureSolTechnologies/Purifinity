package com.puresol.coding.client.wizards;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.ProjectAnalyzerFactory;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.preferences.NewAnalysisPreferencePage;
import com.puresol.coding.client.utils.PlatformUtils;
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
	List<String> dirIncludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.DIRECTORY_INCLUDES).split("\n"));
	List<String> dirExcludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.DIRECTORY_EXCLUDES).split("\n"));
	List<String> fileIncludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.FILE_INCLUDES).split("\n"));
	List<String> fileExcludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.FILE_EXCLUDES).split("\n"));
	boolean ignoreHidden = preferenceStore
		.getBoolean(NewAnalysisPreferencePage.IGNORE_HIDDEN);
	FileSearchConfiguration searchConfiguration = new FileSearchConfiguration(
		dirIncludes, dirExcludes, fileIncludes, fileExcludes,
		ignoreHidden);
	String name = generalSettingsPage.getProjectName();
	String sourceDirectory = generalSettingsPage.getSourceDirectory();
	ProjectAnalyzer job = ProjectAnalyzerFactory.create(new File(
		sourceDirectory),
		new File(PlatformUtils.getWorkspaceDirectory(), name),
		searchConfiguration);
	job.schedule();
	return true;
    }
}
