package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;

@Singleton
public class SessionProducer {

	@Inject
	private Logger logger;

	@Inject
	private Cluster cluster;

	@Produces
	@Singleton
	@Named(CassandraKeyspaces.ANALYSIS)
	public Session getAnalysisSession() {
		logger.info("Creating Cassandra Analysis Session...");
		Session session = cluster
				.connect(CassandraElementNames.ANALYSIS_KEYSPACE);
		logger.info("Cassandra Analysis Session created.");
		return session;
	}

	@Produces
	@Singleton
	@Named(CassandraKeyspaces.EVALUATION)
	public Session getEvaluationSession() {
		logger.info("Creating Cassandra Evaluation Session...");
		Session session = cluster
				.connect(CassandraElementNames.EVALUATION_KEYSPACE);
		logger.info("Cassandra Evaluation Session created...");
		return session;
	}

	public void closeAnalysisKeyspaceSession(
			@Disposes @Named(CassandraKeyspaces.ANALYSIS) Session session) {
		logger.info("Closing Cassandra Analysis Session...");
		cluster.close();
		logger.info("Cassandra Analysis Session closed.");
	}

	public void closeEvaluationKeyspaceSession(
			@Disposes @Named(CassandraKeyspaces.EVALUATION) Session session) {
		logger.info("Closing Cassandra Evaluation Session...");
		cluster.close();
		logger.info("Cassandra Evaluation Session closed.");
	}
}
