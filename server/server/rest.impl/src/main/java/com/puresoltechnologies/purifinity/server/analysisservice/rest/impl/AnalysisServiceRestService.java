package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.RepositoryTypes;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;

public class AnalysisServiceRestService implements AnalysisServiceRestInterface {

	@Inject
	private RepositoryTypePluginService repositoryTypePluginService;

	@Inject
	private AnalyzerPluginService analyzerPluginService;

	@Override
	public AvailableAnalyzers getAnalyzers() throws IOException {
		Collection<AnalyzerInformation> analyzers = analyzerPluginService
				.getServices();
		return new AvailableAnalyzers(analyzers);
	}

	@Override
	public RepositoryTypes getRepositoryTypes() throws IOException {
		return new RepositoryTypes(new LinkedHashSet<>(
				repositoryTypePluginService.getServices()));
	}

}
