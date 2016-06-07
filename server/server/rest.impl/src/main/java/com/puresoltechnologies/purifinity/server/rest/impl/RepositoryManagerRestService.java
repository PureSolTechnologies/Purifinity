package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryManager;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.purifinity.server.rest.api.RepositoryManagerRestInterface;

public class RepositoryManagerRestService implements RepositoryManagerRestInterface {

    @Inject
    private RepositoryManager repositoryService;

    @Override
    public Collection<RepositoryServiceInformation> getRepositories()
	    throws IOException {
	return repositoryService.getRepositoryTypes();
    }

    @Override
    public RepositoryServiceInformation getRepository(
	    String repositoryTypeId) {
	return repositoryService.getRepositoryType(repositoryTypeId);
    }
}
