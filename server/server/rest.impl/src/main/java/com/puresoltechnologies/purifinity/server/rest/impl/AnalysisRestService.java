package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;
import javax.jms.JMSException;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.rest.api.AnalysisRestInterface;

public class AnalysisRestService implements AnalysisRestInterface {

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

    @Override
    public AnalyzerServiceInformation getAnalyzer(String analyzerId) {
	return analysisService.getAnalyzer(analyzerId);
    }

    @Override
    public Set<ConfigurationParameter<?>> getConfiguration(String analyzerId) {
	return analysisService.getConfiguration(analyzerId);
    }

    @Override
    public void enable(String analyzerId) {
	analysisService.setActive(analyzerId, true);
    }

    @Override
    public void disable(String analyzerId) {
	analysisService.setActive(analyzerId, false);
    };
}
