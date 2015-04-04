package com.puresoltechnologies.purifinity.server.plugin.subversion;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.repositories.AbstractRepositoryServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
@EJBFacade
public class SubversionRepositoryRegistration extends
		AbstractRepositoryServiceRegistration {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"repository.git.plugin", "repository.git.ejb",
			RepositoryLocation.class, SubversionRepository.class);

	@PostConstruct
	public void registraion() {
		register(RepositoryServiceManagerRemote.class,
				RepositoryServiceManagerRemote.JNDI_NAME,
				SubversionRepositoryPlugin.INFORMATION, JNDI_ADDRESS,
				SubversionRepository.INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(RepositoryServiceManagerRemote.class,
				RepositoryServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return SubversionRepository.NAME;
	}

	@Override
	public RepositoryServiceInformation getServiceInformation() {
		return SubversionRepository.INFORMATION;
	}
}
