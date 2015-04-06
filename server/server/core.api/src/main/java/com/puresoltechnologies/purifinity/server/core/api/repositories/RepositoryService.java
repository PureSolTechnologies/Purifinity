package com.puresoltechnologies.purifinity.server.core.api.repositories;

import java.util.Collection;

import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers an new analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface RepositoryService {

	public static final String REPOSITORY_ID_PROPERTY = "repository.id";

	public Collection<RepositoryServiceInformation> getRepositoryTypes();

	public RepositoryServiceInformation getRepositoryType(
			String repositoryTypeId);

}
