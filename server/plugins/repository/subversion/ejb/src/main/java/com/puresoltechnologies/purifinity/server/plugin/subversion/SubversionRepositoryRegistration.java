package com.puresoltechnologies.purifinity.server.plugin.subversion;

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
public class SubversionRepositoryRegistration extends
		AbstractRepositoryServiceRegistration {

	@PostConstruct
	public void registraion() {
		register(RepositoryServiceManagerRemote.class,
				RepositoryServiceManagerRemote.JNDI_NAME,
				SubversionRepositoryPlugin.INFORMATION,
				SubversionRepository.JNDI_ADDRESS,
				SubversionRepository.INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(RepositoryServiceManagerRemote.class,
				RepositoryServiceManagerRemote.JNDI_NAME,
				SubversionRepository.JNDI_ADDRESS);
	}

	@Override
	@Lock(LockType.READ)
	public String getName() {
		return SubversionRepository.NAME;
	}

	@Override
	@Lock(LockType.READ)
	public RepositoryServiceInformation getServiceInformation() {
		return SubversionRepository.INFORMATION;
	}
}
