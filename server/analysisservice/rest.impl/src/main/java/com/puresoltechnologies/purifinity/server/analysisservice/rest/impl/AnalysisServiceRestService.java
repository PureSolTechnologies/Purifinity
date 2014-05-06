package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;

public class AnalysisServiceRestService implements AnalysisServiceRestInterface {

	@Inject
	private AnalyzerPluginService analyzerRegistration;

	@Override
	public AvailableAnalyzers getAnalyzers() throws IOException {
		Collection<AnalyzerInformation> analyzers = analyzerRegistration
				.getServices();
		return new AvailableAnalyzers(analyzers);
	}

}
