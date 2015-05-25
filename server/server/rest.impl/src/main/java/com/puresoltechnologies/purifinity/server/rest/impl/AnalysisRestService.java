package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.jms.JMSException;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
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
	public void abortCurrentRun(String projectId) {
		analysisService.abortCurrentRun(projectId);
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
