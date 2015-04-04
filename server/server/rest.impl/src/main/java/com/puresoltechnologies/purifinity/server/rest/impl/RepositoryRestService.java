package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryService;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.purifinity.server.rest.api.RepositoryRestInterface;

public class RepositoryRestService implements RepositoryRestInterface {

    @Inject
    private RepositoryService repositoryService;

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
