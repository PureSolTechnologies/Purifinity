package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.inject.Inject;

import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceRegistration;
import com.puresoltechnologies.purifinity.server.database.cassandra.PluginsKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

public abstract class AbstractAnalyzerServiceRegistration extends
		AbstractServiceRegistration<AnalyzerServiceInformation> implements
		AnalyzerRemotePlugin {

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
