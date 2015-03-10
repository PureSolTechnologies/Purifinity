package com.puresoltechnologies.purifinity.server.metrics.mccabe.db;

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
    @McCabeMetricEvaluatorStoreKeyspace
    public Session getAnalysisSession() {
	logger.info("Creating Cassandra McCabe Metric Evaluator Session...");
	Session session = cluster
		.connect(McCabeMetricEvaluatorStoreKeyspace.NAME);
	logger.info("Cassandra McCabe Metric Evaluator Session created.");
	return session;
    }

    public void closeAnalysisKeyspaceSession(
	    @Disposes @McCabeMetricEvaluatorStoreKeyspace Session session) {
	session.close();
    }
}
