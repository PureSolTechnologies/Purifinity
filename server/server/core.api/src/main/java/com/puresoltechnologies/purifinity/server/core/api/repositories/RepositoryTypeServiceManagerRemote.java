package com.puresoltechnologies.purifinity.server.core.api.repositories;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

@Remote
public interface RepositoryTypeServiceManagerRemote extends
		ServiceManager<RepositoryType> {

	public static final String JNDI_NAME = "java:global/server.app/server.core.impl/RepositoryTypeServiceManagerImpl!com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypeServiceManagerRemote";

}
