package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceManager;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

@Singleton
@EJBFacade
public class RepositoryServiceManagerImpl extends
		AbstractServiceManager<RepositoryServiceInformation> implements
		RepositoryServiceManager, RepositoryServiceManagerRemote {

	public RepositoryServiceManagerImpl() {
		super("Repository Type Service Manager");
	}
}
