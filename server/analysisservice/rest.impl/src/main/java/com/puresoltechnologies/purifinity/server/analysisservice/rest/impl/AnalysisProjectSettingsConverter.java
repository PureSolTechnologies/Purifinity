package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.util.Properties;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;

public class AnalysisProjectSettingsConverter {

	public static AnalysisProjectSettings fromREST(
			com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisProjectSettings settingsREST) {
		String name = settingsREST.getName();
		String description = settingsREST.getDescription();
		FileSearchConfiguration fileSearchConfiguration = FileSearchConfigurationConverter
				.fromREST(settingsREST.getFileSearchConfiguration());
		Properties repositoryLocation = settingsREST.getRepositoryLocation();
		return new AnalysisProjectSettings(name, description,
				fileSearchConfiguration, repositoryLocation);
	}
}
