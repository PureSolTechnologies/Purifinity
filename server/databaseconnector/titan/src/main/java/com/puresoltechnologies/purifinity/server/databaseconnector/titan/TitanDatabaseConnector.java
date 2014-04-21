package com.puresoltechnologies.purifinity.server.databaseconnector.titan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Produces;

import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnectionException;
import com.puresoltechnologies.purifinity.framework.store.db.TitanConnection;
import com.thinkaurelius.titan.core.TitanGraph;

public class TitanDatabaseConnector {

	@PostConstruct
	private void initialize() throws CassandraConnectionException {
		TitanConnection.connect();
	}

	@PreDestroy
	private void destroy() {
		TitanConnection.disconnect();
	}

	@Produces
	public TitanGraph getCluster() {
		return TitanConnection.getGraph();
	}

}
