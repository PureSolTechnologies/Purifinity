package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.db;

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
    @BasicCoCoMoEvaluatorStoreKeyspace
    public Session getAnalysisSession() {
	logger.info("Creating Cassandra Basic CoCoMo Evaluator Session...");
	Session session = cluster
		.connect(BasicCoCoMoEvaluatorStoreKeyspace.NAME);
	logger.info("Cassandra Basic CoCoMo Evaluator Session created.");
	return session;
    }

    public void closeAnalysisKeyspaceSession(
	    @Disposes @BasicCoCoMoEvaluatorStoreKeyspace Session session) {
	session.close();
    }
}
