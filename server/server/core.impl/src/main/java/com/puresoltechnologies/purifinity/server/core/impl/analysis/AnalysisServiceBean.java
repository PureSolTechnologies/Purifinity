package com.puresoltechnologies.purifinity.server.core.impl.analysis;

import java.util.Collection;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Queue;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.common.jms.JMSMessageSender;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStatusInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManager;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.queues.ProjectAnalysisStartQueue;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@Stateless
public class AnalysisServiceBean implements AnalysisService {

    @Resource(mappedName = ProjectAnalysisStartQueue.NAME)
    private Queue projectAnalysisStartQueue;

    @Inject
    private EventLoggerRemote eventLogger;

    @Inject
    private JMSMessageSender messageSender;

    @Inject
    private AnalyzerServiceManager analyzerRegistration;

    @Inject
    private EvaluatorServiceManager evaluatorRegistration;

    @Inject
    private RepositoryServiceManager repositoryTypePluginService;

    @Inject
    private AnalysisProcessStateTracker processTracker;

    @PostConstruct
    public void initialize() {
	eventLogger.logEvent(AnalysisServiceEvents.createStartupEvent());
    }

    @PreDestroy
    public void shutdown() {
	eventLogger.logEvent(AnalysisServiceEvents.createShutdownEvent());
    }

    @Override
    public void triggerNewRun(String projectId) throws JMSException {
	messageSender.sendMessage(projectAnalysisStartQueue, projectId);
    }

    @Override
    public void abortCurrentRun(String projectId) {
	AnalysisProcessStatusInformation processState = processTracker
		.readProcessState(projectId);
	long runId = processState.getRunId();
	processTracker.changeProcessState(projectId, runId,
		AnalysisProcessTransition.REQUEST_ABORT);
    }

    @Override
    public Collection<AnalyzerServiceInformation> getAnalyzers() {
	return analyzerRegistration.getServices();
    }

    @Override
    public AnalyzerServiceInformation getAnalyzer(String analyzerId) {
	for (AnalyzerServiceInformation information : analyzerRegistration
		.getServices()) {
	    if (information.getId().equals(analyzerId)) {
		return information;
	    }
	}
	return null;
    }

    @Override
    public Set<ConfigurationParameter<?>> getConfiguration(String analyzerId) {
	return analyzerRegistration.getInstanceById(analyzerId)
		.getConfigurationParameters();
    }

    @Override
    public boolean isEnabled(String analyzerId) {
	return analyzerRegistration.isActive(analyzerId);
    }

    @Override
    public void setActive(String analyzerId, boolean active) {
	analyzerRegistration.setActive(analyzerId, active);
    }
}
