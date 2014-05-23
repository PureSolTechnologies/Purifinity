package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

public class AnalysisServiceRestService implements AnalysisServiceRestInterface {

	@Inject
	private AnalysisService analysisService;

	@Override
	public Collection<AnalyzerInformation> getAnalyzers() throws IOException {
		return analysisService.getAnalyzers();
	}

	@Override
	public Collection<RepositoryType> getRepositoryTypes() throws IOException {
		return analysisService.getRepositoryTypes();
	}

	@Override
	public void triggerNewAnalysisRun(UUID projectUUID) {
		try {
			analysisService.triggerNewAnalysis(projectUUID);
		} catch (AnalysisStoreException | AnalyzerException e) {
			throw new RuntimeException(
					"Triggered analysis run finished with exception.", e);
		}
	}
}
