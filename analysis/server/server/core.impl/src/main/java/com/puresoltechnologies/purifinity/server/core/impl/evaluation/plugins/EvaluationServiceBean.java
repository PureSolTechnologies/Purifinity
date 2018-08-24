package com.puresoltechnologies.purifinity.server.core.impl.evaluation.plugins;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluationService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@ApplicationScoped
public class EvaluationServiceBean implements EvaluationService {

    @Inject
    private EventLoggerRemote eventLogger;

    @Inject
    private EvaluatorServiceManager evaluatorRegistration;

    @PostConstruct
    public void initialize() {
	eventLogger.logEvent(EvaluationServiceEvents.createStartupEvent());
    }

    @PreDestroy
    public void shutdown() {
	eventLogger.logEvent(EvaluationServiceEvents.createShutdownEvent());
    }

    @Override
    public Collection<EvaluatorServiceInformation> getEvaluators() {
	return evaluatorRegistration.getServices();
    }

    @Override
    public EvaluatorServiceInformation getEvaluator(String evaluatorId) {
	for (EvaluatorServiceInformation information : evaluatorRegistration.getServices()) {
	    if (information.getId().equals(evaluatorId)) {
		return information;
	    }
	}
	return null;
    }

    @Override
    public ConfigurationParameter<?>[] getConfiguration(String evaluatorId) {
	return evaluatorRegistration.createInstanceById(evaluatorId).getConfigurationParameters();
    }

    @Override
    public boolean isEnabled(String evaluatorId) {
	return evaluatorRegistration.isActive(evaluatorId);
    }

    @Override
    public void setActive(String evaluatorId, boolean active) {
	evaluatorRegistration.setActive(evaluatorId, active);
    }
}
