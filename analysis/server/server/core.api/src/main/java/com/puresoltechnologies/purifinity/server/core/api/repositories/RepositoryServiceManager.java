package com.puresoltechnologies.purifinity.server.core.api.repositories;

import java.util.Properties;

import javax.ejb.Local;

import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

@Local
public interface RepositoryServiceManager extends ServiceManager<RepositoryServiceInformation, Repository> {

    /**
     * This method creates a {@link Repository} instance out of the repository
     * id. This repository is actually a proxy via remoting to the plugin.
     * 
     * @param repositoryId
     *            is the id of the repository.
     * @return An {@link RepositoryManager} proxy is returned.
     */
    @Override
    public Repository getInstanceById(String repositoryId);

    public RepositoryLocation createFromSerialization(Properties repositoryLocation);

}
