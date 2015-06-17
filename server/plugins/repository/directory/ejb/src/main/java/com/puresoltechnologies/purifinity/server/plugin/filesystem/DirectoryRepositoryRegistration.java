package com.puresoltechnologies.purifinity.server.plugin.filesystem;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.repositories.AbstractRepositoryServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
@EJBFacade
public class DirectoryRepositoryRegistration extends
		AbstractRepositoryServiceRegistration {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"repository.directory.plugin", "repository.directory.ejb",
			Repository.class, DirectoryRepository.class);

	@PostConstruct
	public void registraion() {
		register(RepositoryServiceManagerRemote.class,
				RepositoryServiceManagerRemote.JNDI_NAME,
				DirectoryRepositoryPlugin.INFORMATION, JNDI_ADDRESS,
				DirectoryRepository.INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(RepositoryServiceManagerRemote.class,
				RepositoryServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	@Lock(LockType.READ)
	public String getName() {
		return DirectoryRepository.NAME;
	}

	@Override
	@Lock(LockType.READ)
	public RepositoryServiceInformation getServiceInformation() {
		return DirectoryRepository.INFORMATION;
	}
}
