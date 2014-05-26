package com.puresoltechnologies.purifinity.server.core.impl.analysis;

import java.util.Collection;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Queue;

import com.puresoltechnologies.purifinity.server.common.jms.JMSMessageSender;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginService;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.queues.ProjectAnalysisStartQueue;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@Stateless
public class AnalysisServiceBean implements AnalysisService {

	@Resource(mappedName = ProjectAnalysisStartQueue.NAME)
	private Queue projectAnalysisStartQueue;

	@Inject
	private EventLogger eventLogger;

	@Inject
	private JMSMessageSender messageSender;

	@Inject
	private AnalyzerPluginService analyzerRegistration;

	@Inject
	private RepositoryTypePluginService repositoryTypePluginService;

	@Inject
	private AnalysisProcessStateTracker analysisProcessStateTracker;

	@PostConstruct
	public void initialize() {
		eventLogger.logEvent(AnalysisServiceEvents.createStartupEvent());
	}

	@PreDestroy
	public void shutdown() {
		eventLogger.logEvent(AnalysisServiceEvents.createShutdownEvent());
	}

	@Override
	public void triggerNewAnalysis(UUID projectUUID) throws JMSException {
		analysisProcessStateTracker.startProcess(projectUUID);
		messageSender.sendMessage(projectAnalysisStartQueue,
				projectUUID.toString());
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
