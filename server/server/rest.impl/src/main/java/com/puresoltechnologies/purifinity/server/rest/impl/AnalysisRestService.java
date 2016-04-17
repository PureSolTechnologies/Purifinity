package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.PathParam;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.rest.api.AnalysisRestInterface;

public class AnalysisRestService implements AnalysisRestInterface {

    @Inject
    private AnalysisService analysisService;

    @Override
    public Collection<AnalyzerServiceInformation> getAnalyzers() throws IOException {
	return analysisService.getAnalyzers();
    }

    @Override
    public void triggerNewRun(String projectId) {
	try {
	    analysisService.triggerRunJob(projectId);
	} catch (AnalysisStoreException e) {
	    throw new RuntimeException("Triggered analysis run finished with exception.", e);
	}
    }

    @Override
    public void abortRun(String projectId, long jobId) {
	analysisService.abortRun(jobId);
    }

    @Override
    public AnalyzerServiceInformation getAnalyzer(String analyzerId) {
	return analysisService.getAnalyzer(analyzerId);
    }

    @Override
    public List<ConfigurationParameter<?>> getConfiguration(String analyzerId) {
	return analysisService.getConfiguration(analyzerId);
    }

    @Override
    public Grammar getGrammar(@PathParam("id") String analyzerId) {
	return analysisService.getGrammar(analyzerId);
    }

    @Override
    public boolean isEnabled(String analyzerId) {
	return analysisService.isEnabled(analyzerId);
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
