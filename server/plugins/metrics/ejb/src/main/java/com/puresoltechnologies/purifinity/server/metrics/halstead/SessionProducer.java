package com.puresoltechnologies.purifinity.server.metrics.halstead;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Singleton
public class SessionProducer {

	@Inject
	private Logger logger;

	@Inject
	private Cluster cluster;

	@Produces
	@Singleton
	@HalsteadEvaluatorKeyspace
	public Session getAnalysisSession() {
		logger.info("Creating Cassandra Halstead Evaluator Session...");
		Session session = cluster.connect(HalsteadEvaluatorKeyspace.NAME);
		logger.info("Cassandra Halstead Evaluator Session created.");
		return session;
	}

	public void closeEvaluationKeyspaceSession(
			@Disposes @HalsteadEvaluatorKeyspace Session session) {
		session.close();
	}
}
