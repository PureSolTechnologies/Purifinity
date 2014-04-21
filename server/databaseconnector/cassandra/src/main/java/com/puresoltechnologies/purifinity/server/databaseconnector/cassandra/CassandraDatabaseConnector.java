package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnectionException;

@Singleton
public class CassandraDatabaseConnector {

	@PostConstruct
	private void initialize() throws CassandraConnectionException {
		CassandraConnection.connect();
	}

	@PreDestroy
	private void destroy() {
		CassandraConnection.disconnect();
	}

	@Produces
	public Cluster getCluster() {
		return CassandraConnection.getCluster();
	}

	@Produces
	@Named(CassandraKeyspaces.ANALYSIS)
	public Session getAnalysisSession() {
		return CassandraConnection.getAnalysisSession();
	}

	@Produces
	@Named(CassandraKeyspaces.EVALUATION)
	public Session getEvaluationSession() {
		return CassandraConnection.getAnalysisSession();
	}
}
