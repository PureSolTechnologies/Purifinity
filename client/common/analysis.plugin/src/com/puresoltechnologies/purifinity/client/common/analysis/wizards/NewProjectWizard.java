package com.puresoltechnologies.purifinity.client.common.analysis.wizards;

import java.io.File;

import org.eclipse.core.runtime.ILog;
import org.eclipse.jface.wizard.Wizard;

import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.framework.commons.utils.NotImplementedException;

public class NewProjectWizard extends Wizard {

	private static final ILog logger = Activator.getDefault().getLog();

	private final NewProjectGeneralSettingsPage generalSettingsPage = new NewProjectGeneralSettingsPage();
	private final NewProjectSourceCodeLocationPage sourceCodeLocationPage = new NewProjectSourceCodeLocationPage();

	public NewProjectWizard() {
		super();
		setWindowTitle("New Project");
		addPage(generalSettingsPage);
		addPage(sourceCodeLocationPage);
	}

	@Override
	public boolean performFinish() {
		throw new NotImplementedException("Analysis Project Creation");
		// try {
		// String projectName = getProjectName();
		// String description = getProjectDescription();
		// File sourceDirectory = getSourceDirectory();
		//
		// IPreferenceStore preferenceStore = Activator.getDefault()
		// .getPreferenceStore();
		// FileSearchConfiguration searchConfiguration = PreferencesUtils
		// .getFileSearchConfiguration(preferenceStore);
		// AnalysisStore analysisStore = AnalysisStoreFactory.getFactory()
		// .getInstance();
		// AnalysisProjectSettings analysisSettings = new
		// AnalysisProjectSettings(
		// projectName, description, searchConfiguration,
		// new DirectoryRepositoryLocation(projectName,
		// sourceDirectory).getSerialization());
		// AnalysisProjectInformation analysisInformation = analysisStore
		// .createAnalysisProject(analysisSettings);
		//
		// AnalysisJob job = new AnalysisJob(analysisInformation,
		// analysisSettings);
		// job.schedule();
		// return true;
		// } catch (AnalysisStoreException e) {
		// logger.log(new Status(Status.ERROR, Activator.getDefault()
		// .getBundle().getSymbolicName(),
		// "Could not create new analysis project.", e));
		// return false;
		// }
	}

	public String getProjectName() {
		return generalSettingsPage.getProjectName();
	}

	public String getProjectDescription() {
		return generalSettingsPage.getProjectDescription();
	}

	public File getSourceDirectory() {
		return new File(sourceCodeLocationPage.getSourceDirectory());
	}
}
