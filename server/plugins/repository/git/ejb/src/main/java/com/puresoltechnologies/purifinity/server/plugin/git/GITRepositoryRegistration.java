package com.puresoltechnologies.purifinity.server.plugin.git;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.repositories.AbstractRepositoryServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

@Singleton
@Startup
@EJBFacade
public class GITRepositoryRegistration extends
		AbstractRepositoryServiceRegistration {

	@PostConstruct
	public void registraion() {
		register(RepositoryServiceManagerRemote.class,
				RepositoryServiceManagerRemote.JNDI_NAME,
				GITRepositoryPlugin.INFORMATION, GITRepository.JNDI_ADDRESS,
				GITRepository.INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(RepositoryServiceManagerRemote.class,
				RepositoryServiceManagerRemote.JNDI_NAME,
				GITRepository.JNDI_ADDRESS);
	}

	@Override
	@Lock(LockType.READ)
	public String getName() {
		return GITRepository.NAME;
	}

	@Override
	@Lock(LockType.READ)
	public RepositoryServiceInformation getServiceInformation() {
		return GITRepository.INFORMATION;
	}
}
