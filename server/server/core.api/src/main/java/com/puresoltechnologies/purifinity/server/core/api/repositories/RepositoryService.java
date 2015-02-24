package com.puresoltechnologies.purifinity.server.core.api.repositories;

import java.util.Collection;

import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryTypeServiceInformation;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers an new analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface RepositoryService {

    public Collection<RepositoryTypeServiceInformation> getRepositoryTypes();

    public RepositoryTypeServiceInformation getRepositoryType(
	    String repositoryTypeId);

}
