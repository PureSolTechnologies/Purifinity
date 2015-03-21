package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.db;

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
    @IntermediateCoCoMoEvaluatorStoreKeyspace
    public Session getAnalysisSession() {
	logger.info("Creating Cassandra Intermediate CoCoMo Evaluator Session...");
	Session session = cluster
		.connect(IntermediateCoCoMoEvaluatorStoreKeyspace.NAME);
	logger.info("Cassandra Intermediate CoCoMo Evaluator Session created.");
	return session;
    }

    public void closeAnalysisKeyspaceSession(
	    @Disposes @IntermediateCoCoMoEvaluatorStoreKeyspace Session session) {
	session.close();
    }
}
