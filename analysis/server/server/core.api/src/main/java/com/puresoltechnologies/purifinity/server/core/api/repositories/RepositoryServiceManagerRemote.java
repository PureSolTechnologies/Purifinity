package com.puresoltechnologies.purifinity.server.core.api.repositories;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

@Remote
public interface RepositoryServiceManagerRemote extends ServiceManager<RepositoryServiceInformation, Repository> {

    public static final String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/RepositoryServiceManagerImpl!com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManagerRemote";

}
