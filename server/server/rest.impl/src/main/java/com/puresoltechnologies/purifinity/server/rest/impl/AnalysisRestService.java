package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.rest.api.AnalysisRestInterface;

public class AnalysisRestService implements AnalysisRestInterface {

    @Inject
    private AnalysisService analysisService;

    @Override
    public InputStream getPreAnalysisOutput(String projectId, long runId) throws EvaluationStoreException {
	try {
	    return analysisService.getPreAnalysisOutput(projectId, runId);
	} catch (IOException e) {
	    throw new EvaluationStoreException("Could not read pre-analysis script output.", e);
	}
    }

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
    public ConfigurationParameter<?>[] getConfiguration(String analyzerId) {
	return analysisService.getConfiguration(analyzerId);
    }

    @Override
    public Grammar getGrammar(String analyzerId) {
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
