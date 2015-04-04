package com.puresoltechnologies.purifinity.server.core.api.repositories;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

@Remote
public interface RepositoryServiceManagerRemote extends
		ServiceManager<RepositoryServiceInformation> {

	public static final String JNDI_NAME = "java:global/server.app/server.core.impl/RepositoryServiceManagerImpl!com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManagerRemote";

}
