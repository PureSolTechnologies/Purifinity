package com.puresoltechnologies.purifinity.server.core.api.repositories;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

@Remote
public interface RepositoryTypePluginServiceRemote extends
		PluginService<RepositoryType> {

	public static final String JNDI_NAME = "java:global/server.app/server.core.impl/RepositoryTypePluginServiceImpl!com.puresoltechnologies.purifinity.server.core.api.analysis.RepositoryTypePluginServiceRemote";

}
