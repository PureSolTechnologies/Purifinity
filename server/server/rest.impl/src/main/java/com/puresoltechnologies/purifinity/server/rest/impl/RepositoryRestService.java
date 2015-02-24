package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryService;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryTypeServiceInformation;
import com.puresoltechnologies.purifinity.server.rest.api.RepositoryRestInterface;

public class RepositoryRestService implements RepositoryRestInterface {

    @Inject
    private RepositoryService repositoryService;

    @Override
    public Collection<RepositoryTypeServiceInformation> getRepositories()
	    throws IOException {
	return repositoryService.getRepositoryTypes();
    }

    @Override
    public RepositoryTypeServiceInformation getRepository(
	    String repositoryTypeId) {
	return repositoryService.getRepositoryType(repositoryTypeId);
    }
}
