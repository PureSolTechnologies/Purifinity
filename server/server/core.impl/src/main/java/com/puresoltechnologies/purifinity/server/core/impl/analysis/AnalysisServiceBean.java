package com.puresoltechnologies.purifinity.server.core.impl.analysis;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@Stateless
public class AnalysisServiceBean implements AnalysisService {

	@Inject
	private EventLogger eventLogger;

	@Inject
	private AnalyzerPluginService analyzerRegistration;

	@Inject
	private RepositoryTypePluginService repositoryTypePluginService;

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

	@Override
	public Collection<RepositoryType> getRepositoryTypes() {
		return repositoryTypePluginService.getServices();
	}

}
