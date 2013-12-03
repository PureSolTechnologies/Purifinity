package com.puresoltechnologies.purifinity.client.common.analysis.wizards;

import java.io.File;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;

import com.puresoltechnologies.commons.utils.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.analysis.jobs.AnalysisJob;
import com.puresoltechnologies.purifinity.client.common.analysis.utils.PreferencesUtils;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisStore;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisStoreFactory;
import com.puresoltechnologies.purifinity.coding.analysis.api.DirectoryRepositoryLocation;

public class NewAnalysisWizard extends Wizard {

	private static final ILog logger = Activator.getDefault().getLog();

	private final NewAnalysisGeneralSettingsPage generalSettingsPage = new NewAnalysisGeneralSettingsPage();

	public NewAnalysisWizard() {
		super();
		setWindowTitle("New Analysis");
		addPage(generalSettingsPage);
	}

	@Override
	public boolean performFinish() {
		try {
			String projectName = getProjectName();
			String description = getProjectDescription();
			File sourceDirectory = getSourceDirectory();

			IPreferenceStore preferenceStore = Activator.getDefault()
					.getPreferenceStore();
			FileSearchConfiguration searchConfiguration = PreferencesUtils
					.getFileSearchConfiguration(preferenceStore);
			AnalysisStore analysisStore = AnalysisStoreFactory.getFactory()
					.getInstance();
			AnalysisProjectSettings analysisSettings = new AnalysisProjectSettings(
					projectName, description, searchConfiguration,
					new DirectoryRepositoryLocation(projectName,
							sourceDirectory));
			AnalysisProject analysis = analysisStore
					.createAnalysis(analysisSettings);

			AnalysisJob job = new AnalysisJob(analysis);
			job.schedule();
			return true;
		} catch (AnalysisStoreException e) {
			logger.log(new Status(Status.ERROR, Activator.getDefault()
					.getBundle().getSymbolicName(),
					"Could not create new analysis project.", e));
			return false;
		}
	}

	public String getProjectName() {
		return generalSettingsPage.getProjectName();
	}

	public String getProjectDescription() {
		return generalSettingsPage.getProjectDescription();
	}

	public File getSourceDirectory() {
		return new File(generalSettingsPage.getSourceDirectory());
	}
}
