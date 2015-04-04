package com.puresoltechnologies.purifinity.server.core.api.repositories;

import javax.inject.Inject;

import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceRegistration;
import com.puresoltechnologies.purifinity.server.database.cassandra.PluginsKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

public abstract class AbstractRepositoryServiceRegistration extends
		AbstractServiceRegistration<RepositoryServiceInformation> implements
		RepositoryRemotePlugin {

	@Inject
	@PluginsKeyspace
	private Session session;

	@Inject
	private CassandraPreparedStatements cassandraPreparedStatements;

	@Override
	protected void registerInDatabase() {
	}

	@Override
	protected void unregisterInDatabase() {
	}

}
