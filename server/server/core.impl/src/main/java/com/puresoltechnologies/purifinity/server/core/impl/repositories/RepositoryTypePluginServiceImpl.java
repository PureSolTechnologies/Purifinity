package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginServiceRemote;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

@Singleton
public class RepositoryTypePluginServiceImpl extends
		AbstractPluginService<RepositoryType> implements
		RepositoryTypePluginService, RepositoryTypePluginServiceRemote {

	public RepositoryTypePluginServiceImpl() {
		super("Repository Type Plugin Service");
	}

	@PostConstruct
	public void addDefaultRepositoryTypes() {
		registerService("jndi:dir", DirectoryRepositoryTypeCreator.create(),
				new Properties());
		registerService("jndi:git", GITRepositoryTypeCreator.create(),
				new Properties());
	}
}
