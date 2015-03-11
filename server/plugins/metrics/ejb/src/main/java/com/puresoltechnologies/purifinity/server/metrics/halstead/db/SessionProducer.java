package com.puresoltechnologies.purifinity.server.metrics.halstead.db;

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
    @HalsteadMetricEvaluatorStoreKeyspace
    public Session getAnalysisSession() {
	logger.info("Creating Cassandra Halstead Metric Evaluator Session...");
	Session session = cluster
		.connect(HalsteadMetricEvaluatorStoreKeyspace.NAME);
	logger.info("Cassandra Halstead Metric Evaluator Session created.");
	return session;
    }

    public void closeAnalysisKeyspaceSession(
	    @Disposes @HalsteadMetricEvaluatorStoreKeyspace Session session) {
	session.close();
    }
}
