package com.puresoltechnologies.purifinity.client.common.analysis.wizards;

import java.util.Properties;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.analysis.utils.PreferencesUtils;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;
import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisServiceClient;

public class NewProjectWizard extends Wizard {

	private static final Logger logger = LoggerFactory
			.getLogger(NewProjectWizard.class);

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
		try {
			String projectName = getProjectName();
			String description = getProjectDescription();
			Properties sourceLocationProperties = getSourceLocationProperties();

			IPreferenceStore preferenceStore = Activator.getDefault()
					.getPreferenceStore();
			FileSearchConfiguration searchConfiguration = PreferencesUtils
					.getFileSearchConfiguration(preferenceStore);
			AnalysisStore analysisStore = AnalysisStoreFactory.getFactory()
					.getInstance();
			AnalysisProjectSettings analysisSettings = new AnalysisProjectSettings(
					projectName, description, searchConfiguration,
					sourceLocationProperties);
			AnalysisProjectInformation analysisInformation = analysisStore
					.createAnalysisProject(analysisSettings);

			AnalysisServiceClient client = AnalysisServiceClient.getInstance();
			// TODO
			//
			// AnalysisJob job = new AnalysisJob(analysisInformation,
			// analysisSettings);
			// job.schedule();
			return true;
		} catch (AnalysisStoreException e) {
			logger.error("Could not create new analysis project.", e);
			return false;
		}
	}

	public String getProjectName() {
		return generalSettingsPage.getProjectName();
	}

	public String getProjectDescription() {
		return generalSettingsPage.getProjectDescription();
	}

	public Properties getSourceLocationProperties() {
		return sourceCodeLocationPage.getSourceLocationProperties();
	}
}
