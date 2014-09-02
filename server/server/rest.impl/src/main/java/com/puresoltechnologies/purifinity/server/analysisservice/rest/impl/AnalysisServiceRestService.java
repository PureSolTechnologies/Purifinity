package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;
import javax.jms.JMSException;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryTypeServiceInformation;

public class AnalysisServiceRestService implements AnalysisServiceRestInterface {

	@Inject
	private AnalysisService analysisService;

	@Override
	public Collection<AnalyzerServiceInformation> getAnalyzers() throws IOException {
		return analysisService.getAnalyzers();
	}

	@Override
	public Collection<RepositoryTypeServiceInformation> getRepositoryTypes() throws IOException {
		return analysisService.getRepositoryTypes();
	}

	@Override
	public void triggerNewAnalysisRun(UUID projectUUID) {
		try {
			analysisService.triggerNewAnalysis(projectUUID);
		} catch (JMSException e) {
			throw new RuntimeException(
					"Triggered analysis run finished with exception.", e);
		}
	}
}
