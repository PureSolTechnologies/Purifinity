package com.puresoltechnologies.purifinity.server.analysisservice.core.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalysisService;
import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerRegistration;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@Singleton
public class AnalysisServiceBean implements AnalysisService {

	@Inject
	private EventLogger eventLogger;

	@Inject
	private AnalyzerRegistration analyzerRegistration;

	@PostConstruct
	public void initialize() {
		eventLogger.logEvent(AnalysisServiceEvents.createStartupEvent());
	}

	@PreDestroy
	public void shutdown() {
		eventLogger.logEvent(AnalysisServiceEvents.createShutdownEvent());
	}

}
