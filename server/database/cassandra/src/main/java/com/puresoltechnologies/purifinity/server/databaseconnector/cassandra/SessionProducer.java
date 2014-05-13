package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils.CassandraElementNames;

@Singleton
public class SessionProducer {

	@Inject
	private Logger logger;

	@Inject
	private Cluster cluster;

	@Produces
	@Singleton
	@AnalysisKeyspace
	public Session getAnalysisSession() {
		logger.info("Creating Cassandra Analysis Session...");
		Session session = cluster
				.connect(CassandraElementNames.ANALYSIS_KEYSPACE);
		logger.info("Cassandra Analysis Session created.");
		return session;
	}

	@Produces
	@Singleton
	@EvaluationKeyspace
	public Session getEvaluationSession() {
		logger.info("Creating Cassandra Evaluation Session...");
		Session session = cluster
				.connect(CassandraElementNames.EVALUATION_KEYSPACE);
		logger.info("Cassandra Evaluation Session created...");
		return session;
	}

	public void closeAnalysisKeyspaceSession(
			@Disposes @AnalysisKeyspace Session session) {
		cluster.close();
	}

	public void closeEvaluationKeyspaceSession(
			@Disposes @EvaluationKeyspace Session session) {
		cluster.close();
	}
}
