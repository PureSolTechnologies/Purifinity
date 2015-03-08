package com.puresoltechnologies.purifinity.server.metrics.sloc.db;

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
    @SLOCEvaluatorStoreKeyspace
    public Session getAnalysisSession() {
	logger.info("Creating Cassandra SLOC Evaluator Session...");
	Session session = cluster.connect(SLOCEvaluatorStoreKeyspace.NAME);
	logger.info("Cassandra SLOC Evaluator Session created.");
	return session;
    }

    public void closeAnalysisKeyspaceSession(
	    @Disposes @SLOCEvaluatorStoreKeyspace Session session) {
	session.close();
    }
}
