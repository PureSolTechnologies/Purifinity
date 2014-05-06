package com.puresoltechnologies.purifinity.server.analysisservice.core.impl;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalysisService;
import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@Stateless
public class AnalysisServiceBean implements AnalysisService {

	@Inject
	private EventLogger eventLogger;

	@Inject
	private AnalyzerPluginService analyzerRegistration;

	@PostConstruct
	public void initialize() {
		eventLogger.logEvent(AnalysisServiceEvents.createStartupEvent());
	}

	@PreDestroy
	public void shutdown() {
		eventLogger.logEvent(AnalysisServiceEvents.createShutdownEvent());
	}

	@Override
	public void triggerNewAnalysis() {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<AnalyzerInformation> getAnalyzers() {
		return analyzerRegistration.getServices();
	}

}
