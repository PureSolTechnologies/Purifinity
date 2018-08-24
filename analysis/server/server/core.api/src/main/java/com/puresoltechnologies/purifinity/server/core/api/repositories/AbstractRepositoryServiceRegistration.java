package com.puresoltechnologies.purifinity.server.core.api.repositories;

import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceRegistration;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

public abstract class AbstractRepositoryServiceRegistration
	extends AbstractServiceRegistration<RepositoryServiceInformation>implements RepositoryRemotePlugin {

    @Override
    protected void registerInDatabase() {
    }

    @Override
    protected void unregisterInDatabase() {
    }

}
