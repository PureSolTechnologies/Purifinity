package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.jms.JMSException;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

public class AnalysisServiceRestService implements AnalysisServiceRestInterface {

    @Inject
    private AnalysisService analysisService;

    @Override
    public Collection<AnalyzerServiceInformation> getAnalyzers()
	    throws IOException {
	return analysisService.getAnalyzers();
    }

    @Override
    public void triggerNewRun(String projectId) {
	try {
	    analysisService.triggerNewRun(projectId);
	} catch (JMSException e) {
	    throw new RuntimeException(
		    "Triggered analysis run finished with exception.", e);
	}
    }
}
