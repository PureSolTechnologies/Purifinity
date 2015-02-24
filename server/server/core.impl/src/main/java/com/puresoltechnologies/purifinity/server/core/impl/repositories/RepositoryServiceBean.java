package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypeServiceManager;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryTypeServiceInformation;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@Stateless
public class RepositoryServiceBean implements RepositoryService {

    @Inject
    private EventLoggerRemote eventLogger;

    @Inject
    private RepositoryTypeServiceManager repositoryTypePluginService;

    @PostConstruct
    public void initialize() {
	eventLogger.logEvent(RepositoryServiceEvents.createStartupEvent());
    }

    @PreDestroy
    public void shutdown() {
	eventLogger.logEvent(RepositoryServiceEvents.createShutdownEvent());
    }

    @Override
    public Collection<RepositoryTypeServiceInformation> getRepositoryTypes() {
	return repositoryTypePluginService.getServices();
    }

    @Override
    public RepositoryTypeServiceInformation getRepositoryType(
	    String repositoryTypeId) {
	for (RepositoryTypeServiceInformation information : repositoryTypePluginService
		.getServices()) {
	    if (information.getId().equals(repositoryTypeId)) {
		return information;
	    }
	}
	return null;
    }
}
