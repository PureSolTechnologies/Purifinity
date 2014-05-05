package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerRegistration;
import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;

public class AnalysisServiceRestService implements AnalysisServiceRestInterface {

	@Inject
	private AnalyzerRegistration analyzerRegistration;

	@Override
	public AvailableAnalyzers getAnalyzers() throws IOException {
		Collection<AnalyzerInformation> analyzers = analyzerRegistration
				.getAnalyzers();
		return new AvailableAnalyzers(analyzers);
	}

}
