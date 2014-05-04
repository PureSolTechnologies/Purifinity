package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;
import java.util.HashSet;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;

public class AnalysisServiceRestService implements AnalysisServiceRestInterface {

	@Override
	public AvailableAnalyzers getAnalyzers() throws IOException {
		HashSet<AnalyzerInformation> analyzers = new HashSet<AnalyzerInformation>();
		analyzers.add(new AnalyzerInformation("Analyzer", "1.23",
				"This is the description"));
		return new AvailableAnalyzers(analyzers);
	}

}
