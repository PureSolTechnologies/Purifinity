package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;
import java.util.LinkedHashSet;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.RepositoryTypes;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;

public class AnalysisServiceRestService implements AnalysisServiceRestInterface {

	@Inject
	private AnalysisService analysisService;

	@Override
	public AvailableAnalyzers getAnalyzers() throws IOException {
		return new AvailableAnalyzers(analysisService.getAnalyzers());
	}

	@Override
	public RepositoryTypes getRepositoryTypes() throws IOException {
		return new RepositoryTypes(new LinkedHashSet<>(
				analysisService.getRepositoryTypes()));
	}

	@Override
	public void triggerNewAnalysis() {
		analysisService.triggerNewAnalysis();
	}
}
