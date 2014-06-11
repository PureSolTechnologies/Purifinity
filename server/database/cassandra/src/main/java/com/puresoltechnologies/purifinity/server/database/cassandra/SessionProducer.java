package com.puresoltechnologies.purifinity.server.database.cassandra;

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
	@AnalysisStoreKeyspace
	public Session getAnalysisSession() {
		logger.info("Creating Cassandra Analysis Session...");
		Session session = cluster.connect(AnalysisStoreKeyspace.NAME);
		logger.info("Cassandra Analysis Session created.");
		return session;
	}

	@Produces
	@Singleton
	@EvaluationStoreKeyspace
	public Session getEvaluationSession() {
		logger.info("Creating Cassandra Evaluation Session...");
		Session session = cluster.connect(EvaluationStoreKeyspace.NAME);
		logger.info("Cassandra Evaluation Session created...");
		return session;
	}

	@Produces
	@Singleton
	@ProcessStatesKeyspace
	public Session getProcessStatesSession() {
		logger.info("Creating Process States Session...");
		Session session = cluster.connect(ProcessStatesKeyspace.NAME);
		logger.info("Cassandra Process States Session created.");
		return session;
	}

	@Produces
	@Singleton
	@PreferencesStoreKeyspace
	public Session getPreferencesStoreSession() {
		logger.info("Creating Preferences Store Session...");
		Session session = cluster.connect(PreferencesStoreKeyspace.NAME);
		logger.info("Cassandra Preferences Store Session created.");
		return session;
	}

	public void closeAnalysisKeyspaceSession(
			@Disposes @AnalysisStoreKeyspace Session session) {
		session.close();
	}

	public void closeEvaluationKeyspaceSession(
			@Disposes @EvaluationStoreKeyspace Session session) {
		session.close();
	}

	public void closeProcessStatesKeyspaceSession(
			@Disposes @ProcessStatesKeyspace Session session) {
		session.close();
	}

	public void closePreferencesStoreKeyspaceSession(
			@Disposes @PreferencesStoreKeyspace Session session) {
		session.close();
	}
}
